package com.hwua.erhai.vo;

public class Request {
    private String requestType;

    public Request() {
    }

    public Request(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
