package com.eqchu.project.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@Component
public class CommonUtils {

    public static boolean isEmptyString(String s){
        return s == null || "".equals(s.trim());
    }

    //获取一个给定位数的验证码，只包含数字
    public static String getNumberVerrify(int i) {
        StringBuilder sb = new StringBuilder(i);
        while(i-- > 0){
            sb.append((int)Math.floor(Math.random()*10));
        }
        return sb.toString();
    }

    public static String getNowTimeFormat(String format){
        String timeString = null;
        try {
            Date now = new Date();
            DateFormat sdf = new SimpleDateFormat(format);
            timeString  = sdf.format(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeString;
    }

    public static String addDate(int timeScale, int count, String formate) throws Exception{
        Calendar ca = Calendar.getInstance();
        ca.add(timeScale,count);
        return new SimpleDateFormat(formate).format(ca.getTime());
    }
}
