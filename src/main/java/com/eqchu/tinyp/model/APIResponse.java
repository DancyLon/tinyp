package com.eqchu.tinyp.model;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class APIResponse {

    private int errorCode;
    private String state;
    private Object data;
    private String msg;

    public APIResponse(){}

    public APIResponse(int code, String state, Object data, String msg) {
        this.errorCode = code;
        this.state = state;
        this.data = data;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public APIResponse setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getState() {
        return state;
    }

    public APIResponse setState(String state) {
        this.state = state;
        return this;
    }

    public Object getData() {
        return data;
    }

    public APIResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public APIResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public String toJSONString(){
        JSONObject json = this.toJSONObject();
        return json.toJSONString();
    }

    public JSONObject toJSONObject(){
        JSONObject json = new JSONObject();
        json.put("state",this.state);
        json.put("data",this.data);
        json.put("msg",this.msg);
        return json;
    }
}
