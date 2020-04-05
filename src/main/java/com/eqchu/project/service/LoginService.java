package com.eqchu.project.service;

import com.eqchu.project.httpRequest.Http;
import com.eqchu.project.utils.CommonUtls;
import com.eqchu.project.utils.TencentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LoginService {

    @Autowired
    private Http http;

    //缓存客户信息
    private Map<String,Map<String,Object>> userInfo = new HashMap();

    //获取短信验证码
    public String getMsgVerify(String phoneNumb) {
        String randomVerify = CommonUtls.getNumberVerrify(6);
        System.out.println("random verify:"+randomVerify);
        Map<String,Object> paras = null;
        String response = null;
        String url = null;
        try {
            url = TencentUtils.getMsgVerifyURL(phoneNumb,randomVerify);
            System.out.println("url="+url);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    //手机号快速登陆
    public boolean loginByPhoneNumber(String phoneNumber, String verify) {
        if (userInfo.get(phoneNumber) == null || userInfo.get(phoneNumber).get("verify") == null
            || !userInfo.get(phoneNumber).get("verify").equals(verify)) {
            return false;
        }else
            return true;
    }
}
