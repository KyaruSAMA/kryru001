package com.hwua.erhai.vo;

public class DoCarUpdateUsableResponse extends Response{
    private long carId;
    private int usable;

    public DoCarUpdateUsableResponse() {

    }
    public DoCarUpdateUsableResponse(int code, String msg, long carId, int usable) {
        super(code, msg);
        this.carId = carId;
        this.usable = usable;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }
}
