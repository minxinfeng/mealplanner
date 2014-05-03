package com.threeone.mealplanner.model.entity;

public class OrderInfo {
    private Integer orderid;

    private Integer mealid;

    private String contactinfo;

    private Integer actualpeoplenum;

    private Integer status;

    private Integer operationuserid;

    private Integer seatid;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getMealid() {
        return mealid;
    }

    public void setMealid(Integer mealid) {
        this.mealid = mealid;
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo == null ? null : contactinfo.trim();
    }

    public Integer getActualpeoplenum() {
        return actualpeoplenum;
    }

    public void setActualpeoplenum(Integer actualpeoplenum) {
        this.actualpeoplenum = actualpeoplenum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperationuserid() {
        return operationuserid;
    }

    public void setOperationuserid(Integer operationuserid) {
        this.operationuserid = operationuserid;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }
}