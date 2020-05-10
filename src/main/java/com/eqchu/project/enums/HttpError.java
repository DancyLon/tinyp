package com.eqchu.project.enums;

/**
 * 自定义http请求错误
 * */
public enum HttpError {
    AUTH_FAILED(401,"请求鉴权失败"),
    EXPIRE(452,"请求过期"),
    FREQUENLY(453,"请求过于频繁"),
    VERIFY_FREQUENLY(454,"验证码请求过于频繁"),
    VERIFY_LIMIT(455,"验证码次数限制，每日100次已用完");

    final private int errorCode;
    final private String errorMsg;

    public static String getMsgByCode(int code){
        for (HttpError e:HttpError.values()
             ) {
            if (e.errorCode == code) {
                return e.getErrorMsg();
            }
        }
        return null;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    private HttpError(int errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    };
}
