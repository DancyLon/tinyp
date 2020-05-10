package com.eqchu.project.model;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class APIResponse {

    private int errorCode;
    private String state;
    private Object body;
    private String msg;

    public APIResponse(){}

    public APIResponse(int code,String state, Object body, String msg) {
        this.errorCode = code;
        this.state = state;
        this.body = body;
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

    public Object getBody() {
        return body;
    }

    public APIResponse setBody(Object body) {
        this.body = body;
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
        json.put("body",this.body);
        json.put("msg",this.msg);
        return json;
    }
}
