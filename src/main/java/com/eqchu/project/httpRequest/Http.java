package com.eqchu.project.httpRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Component
public class Http {

    @Autowired
    private RestTemplate client;

    public Object getMsgVerify(String url) {
        String re = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type","application/x-www-form-urlencoded");
            HttpEntity<String> entry = new HttpEntity<String>(null,headers);
            ResponseEntity<String> response =
                    client.exchange(URI.create(url),HttpMethod.GET,entry,String.class);
            re = response.getBody();
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

}
