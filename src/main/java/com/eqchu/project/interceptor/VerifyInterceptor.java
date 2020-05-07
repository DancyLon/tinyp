package com.eqchu.project.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.eqchu.project.enums.HttpError;
import com.eqchu.project.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码接口拦截器，
 * 同一个号码请求间隔必须大于60秒
 * 同一个号码每天请求次数不能超过100
 * */
@Component
public class VerifyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String phoneNumber =  req.getParameter("phoneNumber");
        Map<String,Object> map = null;
        JSONObject json = new JSONObject();
        json.put("state","failed");
        json.put("body",new HashMap());
        if(phoneNumber!=null){
            map = LoginService.userInfo.get(phoneNumber);
            if(map != null){
                Object o = map.get("verifyTime");
                if (o != null) {
                    if (System.currentTimeMillis() - Long.parseLong(o.toString()) < 1000*60) {
                        res.setStatus(HttpError.VERIFY_FREQUENLY.getErrorCode());
                        json.put("msg",HttpError.VERIFY_FREQUENLY.getErrorMsg());
                        res.getWriter().write(json.toJSONString());
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
