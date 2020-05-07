package com.eqchu.project.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

@ControllerAdvice(basePackages = "com.eqchu.project.controller")
public class ControllerActivity implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest req, ServerHttpResponse res) {
        JSONObject ob = new JSONObject();

        if(o == null){
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            ob.put("state","failed");
            ob.put("body",new HashMap());
            ob.put("msg",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        } else {
            res.setStatusCode(HttpStatus.OK);
            ob.put("state","ok");
            ob.put("body",o);
            ob.put("msg","");
        }
        return ob;
    }
}
