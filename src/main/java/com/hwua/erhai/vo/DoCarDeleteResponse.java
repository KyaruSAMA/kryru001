package com.hwua.erhai.vo;



public class DoCarDeleteResponse extends Response {
    private long carId;
    public DoCarDeleteResponse() {

    }
    public DoCarDeleteResponse(int code, String msg, long carId) {
        super(code, msg);
        this.carId = carId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}
