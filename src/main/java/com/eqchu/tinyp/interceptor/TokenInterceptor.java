package com.eqchu.tinyp.interceptor;

import com.eqchu.tinyp.enums.HttpError;
import com.eqchu.tinyp.model.APIResponse;
import com.eqchu.tinyp.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 需要校验用户token的接口
 * 根据token查询用户状态，如果没有登陆则要求用户先登陆再操作
 * */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    Logger logger  = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
//        logger.info("===== Token Interceptor =====");
//        if(req.getMethod().equals("OPTIONS")){
//            res.setStatus(200);
//            return true;
//        }
//        logger.info("request uri:"+req.getRequestURI());
//        String token = req.getHeader("Token");
//        if(token != null && !"".equals(token)){
//            Map<String,Map<String,Object>> userMap = LoginService.userInfo;
//            for (String phoneNumber: userMap.keySet()) {
//                Map<String,Object> map = userMap.get(phoneNumber);
//                if (map.get("token") != null && map.get("token").equals(token)) {
//                    if(map.get("status")!=null && Integer.parseInt(map.get("status").toString()) == 1)
//                        return true;
//                }
//            }
//        }
//        logger.info("===== token authorization failed =====");
//        APIResponse response = new APIResponse();
//        response.setMsg(HttpError.NO_TOKEN.getErrorMsg());
//        response.setBody(new HashMap());
//        response.setState("failed");
//        res.setStatus(HttpError.NO_TOKEN.getErrorCode());
//        res.getWriter().write(response.toJSONString());

        return true;
    }
}
