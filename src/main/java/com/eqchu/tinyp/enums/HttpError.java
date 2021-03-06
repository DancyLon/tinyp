package com.eqchu.tinyp.enums;

/**
 * 自定义http请求错误
 * */
public enum HttpError {
    AUTH_FAILED(401,"请求鉴权失败"),
    EXPIRE(452,"请求过期"),
    FREQUENLY(453,"请求过于频繁"),
    VERIFY_FREQUENLY(454,"验证码请求过于频繁"),
    VERIFY_LIMIT(455,"验证码次数限制，每日100次已用完"),
    VERIFY_ERROR(456,"短信验证码接口异常,请重新发送"),
    VERIFY_NOT_CORRECT(457,"验证码不正确"),
    VERIFY_OUTTIME(458,"验证码超时，请重新发送"),
    LOGINOUT_ERROR(459,"注销失败"),
    NO_TOKEN(460,"该用户没有登陆，请先登陆");

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
