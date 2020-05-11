package com.eqchu.project.service;

import com.eqchu.project.httpRequest.Http;
import com.eqchu.project.utils.CommonUtils;
import com.eqchu.project.utils.TencentUtils;
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
    public static Map<String,Map<String,Object>> userInfo = new HashMap();

    //获取短信验证码
    public String getMsgVerify(String phoneNumb) throws Exception{
        String randomVerify = CommonUtils.getNumberVerrify(6);
        logger.info("random verify:"+randomVerify);
        Map<String,Object> paras = null;
        String response = null;
        String url = null;
        url = TencentUtils.getMsgVerifyURL(phoneNumb,randomVerify);
        logger.info("url="+url);
        response = http.getMsgVerify(url);
        if ("ok" == response) {
            Map<String,Object> map = null;
            if (userInfo.get(phoneNumb) == null) {
                map = new HashMap();
                userInfo.put(phoneNumb,map);
            }else{
                map = userInfo.get(phoneNumb);
            }
            map.put("verify",randomVerify);
        }
        return response;
    }

    //手机号快速登陆
    public boolean loginByPhoneNumber(String phoneNumber, String verify) {
        if (userInfo.get(phoneNumber) == null || userInfo.get(phoneNumber).get("verify") == null
            || !userInfo.get(phoneNumber).get("verify").equals(verify)) {
            return false;
        }else{
            Query query = new Query(Criteria.where("phone_number").is(phoneNumber));
            Document one = mongo.findOne(query, Document.class,"web_users");
            String nowString = CommonUtils.getNowTimeFormat("yyyy-MM-dd HH:mm:ss");
            if (one == null) {
                one = new Document();
                one.append("phone_number",phoneNumber).append("join_time",nowString)
                    .append("update_time",nowString);
                mongo.insert(one,"web_users");
            }else{
                Update update = new Update();
                update.set("update_time",nowString);
                mongo.updateFirst(query,update,"web_users");
            }
            return true;
        }
    }
}
