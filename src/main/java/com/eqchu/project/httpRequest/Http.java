package com.eqchu.project.httpRequest;

import com.eqchu.project.enums.HttpError;
import com.eqchu.project.model.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestTemplate client;

    public String getMsgVerify(String url) throws Exception{
        String re = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> entry = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response =
                client.exchange(URI.create(url), HttpMethod.GET, entry, String.class);
        if (response.getStatusCodeValue() == 200) {
            re = "ok";
            logger.info("发送短信成功");
        } else {
            logger.error("短信验证码接口异常："+response.toString());
            throw new ServerException(HttpError.VERIFY_ERROR.getErrorCode(),
                    HttpError.VERIFY_ERROR.getErrorMsg());
        }
        return re;
    }

}
