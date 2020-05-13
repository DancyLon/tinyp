package com.eqchu.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CodeUtils<Logge> {
    private static Logger logger = LoggerFactory.getLogger("CodeUtils");

    public static String encodeAuthByTimestamp(Object timestamp) {
        String s = "timestamp="+timestamp.toString();
        String key = "timestamp";
        return sha256EncodeByKeyAndValue(key,s);
    }

    public static String sha256EncodeByKeyAndValue(String key,String value){
        logger.info("=====sha256 encode key:"+key+" and value:"+value+" =====");
        String s = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec ss = new SecretKeySpec(key.getBytes("UTF-8"),mac.getAlgorithm());
            mac.init(ss);
            byte[] res = mac.doFinal(s.getBytes("UTF-8"));
            s = DatatypeConverter.printBase64Binary(res);
            logger.info("=====final mac byte String "+s+" =====");
        } catch(Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public static String getToken(String phoneNumber, long nowTimestamp) {
        String s = "phoneNumber="+phoneNumber+"&timestamp="+nowTimestamp;
        String key = "token";
        return sha256EncodeByKeyAndValue(key,s);
    }
}
