package com.eqchu.project.filter;

import com.eqchu.project.utils.CodeUtils;
import org.slf4j.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.*;

public class ControllerInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);

    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        //-----跨域问题-----
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,Authorization,sessionToken,Token,Timestamp");
        if(req.getMethod().equals("OPTIONS")){
            res.setStatus(200);
            return true;
        }
        //校验时间戳
        Object timestamp = req.getHeader("Timestamp");
        if(timestamp == null){
            log.info("===== There is no Timestamp =====");
            res.setStatus(452);
            return false;
        }else{
            long tt = Long.parseLong(timestamp.toString());
            if(System.currentTimeMillis() - tt > 1000*3600){//超时时间一小时
                log.info("===== 接口过期 =====");
                res.setStatus(452);
                return false;
            }
        }

        //验证Authorizaiton
        String auth = req.getHeader("Authorization");
        if (auth == null || "".equals(auth)) {
            log.info("===== There is no Authorization =====");
            res.setStatus(401);
            return false;
        }else{
            log.info("===== This is the Authorization "+auth+"=====");
            if(!auth.equals(CodeUtils.encodeAuthByTimestamp(timestamp))){
                log.info("=====鉴权失败=====");
                res.setStatus(401);
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
    }
}
