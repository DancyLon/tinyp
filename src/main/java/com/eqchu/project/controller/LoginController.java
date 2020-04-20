package com.eqchu.project.controller;
import java.util.*;
import com.eqchu.project.service.LoginService;
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
            @RequestParam String phoneNumber){
        String re = service.getMsgVerify(phoneNumber);
        return re;
    }

    /**s
     * 用手机号快速登陆
     * @param phoneNumber
     * @param verify
     * */
    @RequestMapping(value = "/by/phoneNumber",method = RequestMethod.GET)
    public Object loginByPhoneNumber(@RequestParam String phoneNumber,
                                     @RequestParam String verify){
        Map<String,String> map = new HashMap();
        if (service.loginByPhoneNumber(phoneNumber,verify)) {
            map.put("status","ok");
            map.put("msg","登陆成功");
        } else{
            map.put("status","fail");
            map.put("msg","登陆失败，验证码不正确");
        }
        return map;
    }

    @RequestMapping(value = "/test")
    public Object test(){
        return "test";
    }
}
