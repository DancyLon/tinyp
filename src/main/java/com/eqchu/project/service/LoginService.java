package com.eqchu.project.service;

import com.eqchu.project.httpRequest.Http;
import com.eqchu.project.utils.CommonUtls;
import com.eqchu.project.utils.TencentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private Http http;

    //获取短信验证码
    public Object getMsgVerify(String phoneNumb) {
        String randomVerify = CommonUtls.getNumberVerrify(6);
        String url = TencentUtils.getMsgVerifyURL(phoneNumb,randomVerify);
        Object response = http.getMsgVerify(url);

        return response;
    }
}
