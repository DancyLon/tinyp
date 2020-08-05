package com.eqchu.tinyp.service;

import com.eqchu.tinyp.enums.HttpError;
import com.eqchu.tinyp.httpRequest.Http;
import com.eqchu.tinyp.model.ServerException;
import com.eqchu.tinyp.utils.CodeUtils;
import com.eqchu.tinyp.utils.CommonUtils;
import com.eqchu.tinyp.utils.TencentUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LoginService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Http http;
    @Autowired
    private MongoTemplate mongo;

    //缓存客户信息
    public static Map<String,Map<String,Object>> userInfo = new Hashtable();

    //获取短信验证码
    public String getMsgVerify(String phoneNumber) throws Exception{

        //校验验证码时效，30秒内不能重复发送
        Map<String,Object> userMap = LoginService.userInfo.get(phoneNumber);
        if(userMap != null){
            Object o = userMap.get("verifyTime");
            if (o != null) {
                if (System.currentTimeMillis() - Long.parseLong(o.toString()) < 1000*30) {
                    throw new ServerException(HttpError.VERIFY_FREQUENLY.getErrorCode(),
                            HttpError.VERIFY_FREQUENLY.getErrorMsg());
                }
            }
        }

        String randomVerify = CommonUtils.getNumberVerrify(6);
        logger.info("random verify:"+randomVerify);
        Map<String,Object> paras = null;
        String response = null;
        String url = null;
        url = TencentUtils.getMsgVerifyURL(phoneNumber,randomVerify);
        logger.info("url="+url);
        response = http.getMsgVerify(url);
        if ("ok" == response) {
            Map<String,Object> map = null;
            if (userInfo.get(phoneNumber) == null) {
                map = new HashMap();
                userInfo.put(phoneNumber,map);
            }else{
                map = userInfo.get(phoneNumber);
            }
            map.put("verify",randomVerify);
            map.put("verifyTime",System.currentTimeMillis());
        }
        return response;
    }

    //手机号快速登陆
    public String loginByPhoneNumber(String phoneNumber, String verify) throws Exception{
        if (userInfo.get(phoneNumber) == null || userInfo.get(phoneNumber).get("verify") == null
            || !userInfo.get(phoneNumber).get("verify").equals(verify)) {
            logger.error("=====验证码不正确=====");
            throw new ServerException(HttpError.VERIFY_NOT_CORRECT.getErrorCode(),
                    HttpError.VERIFY_NOT_CORRECT.getErrorMsg());
        }

        //校验验证码时效，120秒超时
        Map<String,Object> userMap = userInfo.get(phoneNumber);
        Object o = userMap.get("verifyTime");
        if (o != null) {
            if (System.currentTimeMillis() - Long.parseLong(o.toString()) > 1000*120) {
                throw new ServerException(HttpError.VERIFY_OUTTIME.getErrorCode(),
                        HttpError.VERIFY_OUTTIME.getErrorMsg());
            }
        }else
            throw new ServerException(HttpError.VERIFY_ERROR.getErrorCode(),
                    HttpError.VERIFY_ERROR.getErrorMsg());

        long nowTimestamp = System.currentTimeMillis();
        String token = CodeUtils.getToken(phoneNumber,nowTimestamp);
        userMap.put("status",1);
        userMap.put("loginTime",nowTimestamp);
        userMap.put("token",token);
        userMap.put("logoutTime","");

        Query query = new Query(Criteria.where("phone_number").is(phoneNumber));
        Document one = mongo.findOne(query, Document.class,"web_users");
        String nowString = CommonUtils.getTimeFormatByMillis(nowTimestamp,"yyyy-MM-dd HH:mm:ss");
        if (one == null) {
            one = new Document();
            one.putAll(userMap);
            one.append("phone_number",phoneNumber).append("join_time",nowString)
                    .append("update_time",nowString);
            mongo.insert(one,"web_users");
        }else{
            Update update = new Update();
            update.set("update_time",nowString).set("status",1);
            mongo.updateFirst(query,update,"web_users");
        }

        return token;
    }

    public Object logout(String phoneNumber, String token) throws Exception{
        if(userInfo.get(phoneNumber)==null
                || userInfo.get(phoneNumber).get("token")==null
                || !userInfo.get(phoneNumber).get("token").equals(token)){
            throw new ServerException(HttpError.LOGINOUT_ERROR.getErrorCode(),
                    HttpError.LOGINOUT_ERROR.getErrorMsg());
        }
        long timestamp = System.currentTimeMillis();
        userInfo.get(phoneNumber).put("statue",0);
        userInfo.get(phoneNumber).put("logoutTime",timestamp);

        Query query = new Query(Criteria.where("phone_number").is(phoneNumber));
        Document one = mongo.findOne(query, Document.class,"web_users");
        String nowString = CommonUtils.getTimeFormatByMillis(timestamp,"yyyy-MM-dd HH:mm:ss");
        if (one == null) {
            one = new Document();
            one.putAll(userInfo.get(phoneNumber));
            one.append("phone_number",phoneNumber).append("join_time",nowString)
                    .append("update_time",nowString);
            mongo.insert(one,"web_users");
        }else{
            Update update = new Update();
            update.set("update_time",nowString).set("status",0).set("logoutTime",timestamp);
            mongo.updateFirst(query,update,"web_users");
        }
        return true;
    }
}
