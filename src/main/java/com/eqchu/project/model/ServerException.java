package com.eqchu.project.model;

import org.springframework.stereotype.Component;

/**
 * 自定义异常
 * */
@Component
public class ServerException extends RuntimeException{
    private int errorCode;
    private String msg;
    private Exception exception;

    public ServerException(int errorCode,String msg,Exception ex){
        this.errorCode = errorCode;
        this.msg = msg;
        this.exception = ex;
    }

    public ServerException(int errorCode,String msg){
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
