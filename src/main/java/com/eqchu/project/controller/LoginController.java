package com.eqchu.project.controller;

import com.eqchu.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    /**
     * 登陆时获取短信验证码
     * @param phoneNumb
     * */
    @RequestMapping(value = "/msg/verify",method = RequestMethod.GET)
    public Object getMsgVerify(
            @RequestParam(value = "phoneNumb") String phoneNumb){
        return service.getMsgVerify(phoneNumb);
    }
}
