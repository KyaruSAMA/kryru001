package com.hwua.erhai.entity;

public class Record {
    private long id;
    private long userId;
    private long carId;
    private String startDate;
    private String returnDate;
    private double payment;
    private double rent;
    private String model;
    private String comments;
    private String brandName;
    private String categoryName;
    private String userName;
    private int status;

    public Record() {
    }

    public Record(long id, long userId, long carId, String userName, String startDate, String returnDate,double payment,int status) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.userName=userName;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.payment = payment;
        this.status=status;}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Record(long id, long userId, long carId, String startDate, String returnDate, double payment, String model,
                  String comments, String brandName, String categoryName, int status) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.payment = payment;
        this.model = model;
        this.comments = comments;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.status=status;
    }

    public Record(long id, double payment, String model, String comments,
                  String brandName, String categoryName, String startDate, String returnDate) {
        this.id = id;
        this.payment = payment;
        this.model = model;
        this.comments = comments;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }

    Record(long id, long userId, long carId, String startDate, String returnDate, double payment, double rent,
           String model, String comments, String brandName, String categoryName, String userName) {
        super();
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.payment = payment;
        this.rent = rent;
        this.model = model;
        this.comments = comments;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.userName = userName;
    }

    public Record(long id, long userId, long carId, String startDate, String returnDate, double payment) {
        super();
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.payment = payment;
    }

    public Record(long id, long carId,String model,double payment,String comments,String brandName,String categoryName,
                  String startDate, String returnDate) {
        this.id = id;
        this.carId = carId;
        this.model = model;
        this.payment = payment;
        this.comments = comments;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.startDate = startDate;
        this.returnDate = returnDate;

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Record [id=" + id + ", userId=" + userId + ", carId=" + carId + ", startDate=" + startDate
                + ", returnDate=" + returnDate + ", payment=" + payment + ", rent=" + rent + ", model=" + model
                + ", comments=" + comments + ", brandName=" + brandName + ", categoryName=" + categoryName
                + ", userName=" + userName + "]";
    }
}
