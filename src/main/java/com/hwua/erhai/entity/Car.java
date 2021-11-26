package com.hwua.erhai.entity;

public class Car {
    private long id;
    private String carNumber;
    private int brandId;
    private String brandName;
    private String model;
    private String color;
    private int categoryId;
    private String categoryName;
    private String comments;
    private double price;
    private double rent;
    private int status;
    private int usable;

    public Car() {
    }
    public Car(long id, int brandId, String brandName, String model, int categoryId,
               String categoryName, String comments, double rent, int status,int usable,String carNumber,String color,double price) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.model = model;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.comments = comments;
        this.rent = rent;
        this.status = status;
        this.usable=usable;
        this.carNumber=carNumber;
        this.color=color;
        this.price=price;

    }
    public Car(long id, int brandId, String brandName, String model, int categoryId,
               String categoryName, String comments, double rent, int status) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.model = model;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.comments = comments;
        this.rent = rent;
        this.status = status;
    }

    public Car(long id, int brandId, String brandName, String model, int categoryId,
               String categoryName, String comments, double rent, int status, int usable) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.model = model;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.comments = comments;
        this.rent = rent;
        this.status = status;
        this.usable = usable;
    }

    public Car(long id, String carNumber, int brandId, String brandName, String model, String color, int categoryId,
               String categoryName, String comments, double price, double rent, int status, int usable) {
        this.id = id;
        this.carNumber = carNumber;
        this.brandId = brandId;
        this.brandName = brandName;
        this.model = model;
        this.color = color;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.comments = comments;
        this.price = price;
        this.rent = rent;
        this.status = status;
        this.usable = usable;
    }

    public Car(long id, String carNumber, int brandId, String brandName, String model, String color, int categoryId,
               String categoryName, String comments, double price, double rent, int status) {
        this.id = id;
        this.carNumber = carNumber;
        this.brandId = brandId;
        this.brandName = brandName;
        this.model = model;
        this.color = color;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.comments = comments;
        this.price = price;
        this.rent = rent;
        this.status = status;
    }
    public Car(long id,double rent,int usable){
        this.id=id;
        this.rent=rent;
        this.usable=usable;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", carNumber=" + carNumber + ", brandId=" + brandId + ", brandName=" + brandName
                + ", model=" + model + ", color=" + color + ", categoryId=" + categoryId + ", categoryName="
                + categoryName + ", comments=" + comments + ", price=" + price + ", rent=" + rent + ", status=" + status
                + ", usable=" + usable + "]";
    }
}
