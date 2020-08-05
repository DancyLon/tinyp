package com.eqchu.tinyp.config;

import com.eqchu.tinyp.enums.HttpError;
import com.eqchu.tinyp.model.APIResponse;
import com.eqchu.tinyp.model.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@ControllerAdvice(basePackages = "com.eqchu.tinyp.controller")
public class ControllerActivity implements ResponseBodyAdvice<Object> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**处理任何异常*/
    @ExceptionHandler
    @ResponseBody
    public Object handleException(Exception e,HttpServletResponse resonse){
        logger.error("exception：",e);
        APIResponse res = new APIResponse()
                .setState("failed")
                .setData(new HashMap())
                .setErrorCode(500)
                .setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        resonse.setStatus(500);
        return res.toJSONObject();
    }

    /**处理自定义异常*/
    @ExceptionHandler(value = ServerException.class)
    @ResponseBody
    public Object handleServerException(ServerException e, HttpServletResponse resonse){
        logger.error("ServerException："+e.toString());
        APIResponse res = new APIResponse()
                .setState("failed")
                .setData(new HashMap());

        int errorCode = e.getErrorCode();
        String msg = HttpError.getMsgByCode(errorCode);
        if (msg == null) {
            HttpStatus status = HttpStatus.resolve(errorCode);
            if (status == null) {
                msg = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }else{
                msg = status.getReasonPhrase();
            }
        }
        res.setMsg(msg);
        resonse.setStatus(errorCode);
        return res.toJSONObject();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest req, ServerHttpResponse res) {
        APIResponse ob = new APIResponse();
        if(o == null){
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            ob.setState("failed").setData(new HashMap())
                    .setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        } else if(o instanceof APIResponse){
            ob = (APIResponse)o;
        } else {
            res.setStatusCode(HttpStatus.OK);
            ob.setState("ok").setData(o)
                    .setMsg("");
        }
        return ob.toJSONObject();
    }
}
