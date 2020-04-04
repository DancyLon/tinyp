package com.eqchu.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class MainConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate et = new RestTemplate();
        et.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return et;
    }
}
