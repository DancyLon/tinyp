package com.eqchu.project.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;

@ControllerAdvice(basePackages = "com.eqchu.project.controller")
public class ControllerActivity implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //解决跨域问题
        serverHttpResponse.getHeaders().setAccessControlAllowOrigin("*");
        serverHttpResponse.getHeaders().setAccessControlAllowCredentials(true);
        serverHttpResponse.getHeaders().setAccessControlAllowMethods(
                Arrays.asList(HttpMethod.POST,HttpMethod.GET,
                HttpMethod.PATCH,HttpMethod.DELETE,HttpMethod.PUT));
        serverHttpResponse.getHeaders().setAccessControlMaxAge(3600);
        serverHttpResponse.getHeaders().setAccessControlAllowHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept"));
        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        JSONObject ob = new JSONObject();

        if(o == null){
            ob.put("state","fail");
            ob.put("body","");
        } else {
            ob.put("state","ok");
            ob.put("body",o);
        }
        return ob;
    }
}
