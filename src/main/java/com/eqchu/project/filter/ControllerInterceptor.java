package com.eqchu.project.filter;

import org.slf4j.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.*;

public class ControllerInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);

    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        String auth = req.getHeader("Authorization");
        if (auth == null || "".equals(auth)) {
            log.info("===== There is no Authorization =====");
            res.setStatus(401);
        }else{
            log.info("===== This is the Authorization =====");
            
        }

        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,Authorization,sessionToken,Token,Timestamp");

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
    }
}
