package com.eqchu.project.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonUtls {

    //获取一个给定位数的验证码，只包含数字
    public static String getNumberVerrify(int i) {
        StringBuilder sb = new StringBuilder(i);
        while(i-- > 0){
            sb.append(Math.floor(Math.random()*10));
        }
        return sb.toString();
    }
}
