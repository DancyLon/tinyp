package com.eqchu.project.httpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Component
public class Http {
    @Autowired
    private RestTemplate client;

    public Object getMsgVerify(String url) {
        String re = null;

        try {
            Map<String,Object> response = client.getForObject(url,Map.class);
            if (response.get("status").toString().equals("200")) {
                re = "successful";
                System.out.println(response.get("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }
}
