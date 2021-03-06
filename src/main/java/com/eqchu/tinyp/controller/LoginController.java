package com.eqchu.tinyp.controller;
import com.eqchu.tinyp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    /**
     * 登陆时获取短信验证码
     * @param phoneNumber
     * */
    @RequestMapping(value = "/msg/verify",method = RequestMethod.GET)
    public Object getMsgVerify(
            @RequestParam String phoneNumber) throws Exception{
        return service.getMsgVerify(phoneNumber);
    }

    /**
     * 用手机号快速登陆
     * @param phoneNumber
     * @param verify
     * */
    @RequestMapping(value = "/by/phoneNumber",method = RequestMethod.GET)
    public Object loginByPhoneNumber(@RequestParam String phoneNumber,
                                     @RequestParam String verify) throws Exception{
       return service.loginByPhoneNumber(phoneNumber,verify);
    }

    /**
     * 注销的方法
     * @param phoneNumber 手机号
     * @param token 用户token
     * */
    @RequestMapping(value = "/out",method = RequestMethod.GET)
    public Object logout(@RequestParam String phoneNumber,
                           @RequestParam String token) throws Exception{
        return service.logout(phoneNumber,token);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object test() throws Exception{
        return "test";
    }
}
