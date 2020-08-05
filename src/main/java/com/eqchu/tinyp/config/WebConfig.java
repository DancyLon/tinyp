package com.eqchu.tinyp.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.eqchu.tinyp.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new ControllerInterceptor())
                .addPathPatterns("/**").order(1);
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**/login/out",
                        "/**/info/create",
                        "/**/login/test",
                        "/**/info/delete").order(2);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        //解决'Content-Type' cannot contain wildcard type '*'的问题
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);

        //针对字段的处理
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(supportedMediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty,// List字段如果为null,输出为[],而非null
                SerializerFeature.WriteMapNullValue,//加上后，字段为null的也会输出
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为”“,而非null
                SerializerFeature.WriteNullBooleanAsFalse,//Boolean字段如果为null,输出为false,而非null
                SerializerFeature.PrettyFormat  //结果是否格式化,默认为false
        );
        //日期格式化
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(0,converter);//返回是string的话，默认把这个放在最前，否则ResponseAdvisor 处理字符串返回时会报类型不一致
    }
}