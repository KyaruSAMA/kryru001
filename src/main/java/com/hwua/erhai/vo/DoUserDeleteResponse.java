package com.hwua.erhai.vo;

public class DoUserDeleteResponse extends Response{
    private long userId;
    public DoUserDeleteResponse() {}
    public DoUserDeleteResponse(int code, String msg, long userId) {
        super(code, msg);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
