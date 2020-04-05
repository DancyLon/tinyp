package com.eqchu.project;
import java.util.*;
import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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

        Map<String,Object> map = new HashMap<String,Object>();
        if(o instanceof String || o instanceof Collection){
            map.put("data",o);
        } else {
            map.put("data",new HashMap());
        }
        return JSON.toJSONString(map);
    }
}
