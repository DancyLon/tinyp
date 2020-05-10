package com.eqchu.project.interceptor;

import com.eqchu.project.enums.HttpError;
import com.eqchu.project.model.APIResponse;
import com.eqchu.project.utils.CodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**所有接口拦截器，用于接口鉴权，处理跨域问题*/
@Component
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
        APIResponse json = new APIResponse();
        json.setState("failed");
        json.setBody(new HashMap());
        //校验时间戳
        Object timestamp = req.getHeader("Timestamp");
        if(timestamp == null){
            log.info("===== There is no Timestamp =====");
            res.setStatus(HttpError.EXPIRE.getErrorCode());
            json.setMsg(HttpError.EXPIRE.getErrorMsg());
            res.getWriter().write(json.toJSONString());
            return false;
        }else{
            long tt = Long.parseLong(timestamp.toString());
            if(System.currentTimeMillis() - tt > 1000*3600){//超时时间一小时
                log.info("===== interface expire =====");
                res.setStatus(HttpError.EXPIRE.getErrorCode());
                json.setMsg(HttpError.EXPIRE.getErrorMsg());
                res.getWriter().write(json.toJSONString());
                return false;
            }
        }

        //验证Authorizaiton
        String auth = req.getHeader("Authorization");
        if (auth == null || "".equals(auth)) {
            log.info("===== There is no Authorization =====");
            res.setStatus(HttpError.AUTH_FAILED.getErrorCode());
            json.setMsg(HttpError.AUTH_FAILED.getErrorMsg());
            res.getWriter().write(json.toJSONString());
            return false;
        }else{
            log.info("===== This is the Authorization "+auth+"=====");
            if(!auth.equals(CodeUtils.encodeAuthByTimestamp(timestamp))){
                log.info("=====Authorizatino failed=====");
                res.setStatus(HttpError.AUTH_FAILED.getErrorCode());
                json.setMsg(HttpError.AUTH_FAILED.getErrorMsg());
                res.getWriter().write(json.toJSONString());
                return false;
            }
        }
        return true;
    }
}
