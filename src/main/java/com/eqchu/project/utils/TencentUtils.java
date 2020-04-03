package com.eqchu.project.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Component
public class TencentUtils {
    private final static String CHARSET = "UTF-8";

    //根据腾讯云发送短信借口构建的 获取短信借口请求地址的算法
    //详情见 https://cloud.tencent.com/document/product/382/38778

    public static String getMsgVerifyURL(String phoneNumb, String verifyNumber) {
        TreeMap<String, Object> params = new TreeMap<String, Object>(); // TreeMap可以自动排序
        // 实际调用时应当使用随机数，例如：params.put("Nonce", new Random().nextInt(java.lang.Integer.MAX_VALUE));
        params.put("Nonce", new Random().nextInt(100000)); // 公共参数
        // 实际调用时应当使用系统当前时间，例如：   params.put("Timestamp", System.currentTimeMillis() / 1000);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR_OF_DAY,-0);
        long timestamp = ca.getTimeInMillis()/1000;
        params.put("Timestamp", timestamp); // 公共参数
        params.put("SecretId", "AKIDqVDgx0GIZcRFp9p0iAMMOBiz0nLeMgPs"); // 公共参数
        params.put("Action", "SendSms"); // 公共参数
        params.put("Version", "2019-07-11"); // 公共参数
        params.put("PhoneNumberSet.0", "+86"+phoneNumb); // 业务参数
        params.put("TemplateID", "565194"); // 业务参数
        params.put("SmsSdkAppid", "1400340834"); // 业务参数
        params.put("TemplateParamSet.0",verifyNumber);
        params.put("Sign","浅出网络");

        String paraString = getStringToSign(params);
        System.out.println("paraString:"+paraString);
        String signString = null;
        String url = null;
        try {
            signString = sign(paraString,"TedN3SiiTxBCzXo0wb081h8IVTAbmh5E","HmacSHA1");
            System.out.println("signString:"+signString);
            params.put("Signature", signString); // 公共参数
            url = getUrl(params);
            System.out.println("timestamp:"+timestamp);
            System.out.println("url="+url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String sign(String s, String key, String method) throws Exception {
        Mac mac = Mac.getInstance(method);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(s.getBytes(CHARSET));
        return DatatypeConverter.printBase64Binary(hash);
    }

    private static String getStringToSign(TreeMap<String, Object> params) {
        StringBuilder s2s = new StringBuilder("GETsms.tencentcloudapi.com/?");
        // 签名时要求对参数进行字典排序，此处用TreeMap保证顺序
        for (String k : params.keySet()) {
            s2s.append(k).append("=").append(params.get(k).toString()).append("&");
        }
        return s2s.toString().substring(0, s2s.length() - 1);
    }

    private static String getUrl(TreeMap<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder("https://sms.tencentcloudapi.com/?");
        // 实际请求的url中对参数顺序没有要求
        for (String k : params.keySet()) {
            // 需要对请求串进行urlencode，由于key都是英文字母，故此处仅对其value进行urlencode
            url.append(k).append("=").append(URLEncoder.encode(params.get(k).toString(), CHARSET)).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }

    public static void main(String[] args)  {

    }
}
