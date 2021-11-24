package com.hwua.erhai.model;

public class MUserSearch {
    private String userId;
    private String userName;
    private String type;
    public MUserSearch(){
        this("","","");
    }
    public MUserSearch(String userId, String userName, String type) {
        this.userId = userId==null?"":userId;
        this.userName = userName==null?"":userName;
        this.type = type==null?"":type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
