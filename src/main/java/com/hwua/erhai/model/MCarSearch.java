package com.hwua.erhai.model;

public class MCarSearch {
    private String carId;
    private String carBrand;
    private String carCategory;
    private  String priceOrder;

    public MCarSearch(){
        this("","","","unordered");
    }
    public MCarSearch(String carId, String carBrand, String carCategory, String priceOrder) {
        this.carId = carId ==null?"":carId;
        this.carBrand = carBrand==null?"":carBrand;
        this.carCategory = carCategory==null?"":carCategory;
        this.priceOrder = priceOrder==null?"unordered":priceOrder;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }
}
