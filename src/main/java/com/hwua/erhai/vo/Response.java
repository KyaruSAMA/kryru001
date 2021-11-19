package com.hwua.erhai.vo;

public class Response {
    private int code; // code = 200表示正确， code = 400表示错误
    private String msg; // 响应的消息

    public Response() {
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
