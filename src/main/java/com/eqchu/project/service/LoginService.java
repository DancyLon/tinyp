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

    //获取短信验证码
    public Object getMsgVerify(String phoneNumb) {
        String randomVerify = CommonUtls.getNumberVerrify(6);
        System.out.println("random verify:"+randomVerify);
        Map<String,Object> paras = null;
        Object response = null;
        String url = null;
        try {
            url = TencentUtils.getMsgVerifyURL(phoneNumb,randomVerify);
            System.out.println("url="+url);
            response = http.getMsgVerify(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
