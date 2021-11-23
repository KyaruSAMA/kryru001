package com.hwua.erhai.model;

public class MRecordSearch {
    private String carId;
    private String userName;
    public MRecordSearch(){
        this("","");
    }

    public MRecordSearch(String carId, String userName) {
        this.carId = carId;
        this.userName = userName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
