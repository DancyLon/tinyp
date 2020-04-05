package com.eqchu.project.httpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@Component
public class Http {

    @Autowired
    private RestTemplate client;

    public String getMsgVerify(String url) {
        String re = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type","application/x-www-form-urlencoded");
            HttpEntity<String> entry = new HttpEntity<String>(null,headers);
            ResponseEntity<String> response =
                    client.exchange(URI.create(url),HttpMethod.GET,entry,String.class);
            if (response.getStatusCodeValue() == 200) {
                re = "ok";
                System.out.println("发送短信成功");
            }else{
                System.out.println("发送短信失败，错误原因：");
                System.out.println(response.getBody());
                re = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

}
