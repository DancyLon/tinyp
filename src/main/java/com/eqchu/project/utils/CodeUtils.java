package com.eqchu.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class CodeUtils<Logge> {
    private static Logger logger = LoggerFactory.getLogger("CodeUtils");

    public static String encodeAuthByTimestamp(Object timestamp) throws Exception {
        String s = "timestamp="+timestamp.toString();
        String key = "timestamp";
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec ss = new SecretKeySpec(key.getBytes("UTF-8"),mac.getAlgorithm());
        mac.init(ss);
        byte[] res = mac.doFinal(s.getBytes("UTF-8"));
        s = DatatypeConverter.printBase64Binary(res);
        logger.info("=====final mac byte String "+s+" =====");
        return s;
    }
}
