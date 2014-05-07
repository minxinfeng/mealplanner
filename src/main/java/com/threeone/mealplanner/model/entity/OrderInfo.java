package com.threeone.mealplanner.model.entity;

import java.util.Date;

public class OrderInfo {
    private Integer orderid;

    private Integer mealid;

    private Integer restid;

    private Integer userid;

    private String contactinfo;

    private Integer actualpeoplenum;

    private String menuids;

    private Date mealtime;

    private Integer seatid;

    private Integer operationuserid;

    private Integer status;

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

    public Integer getRestid() {
        return restid;
    }

    public void setRestid(Integer restid) {
        this.restid = restid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getMenuids() {
        return menuids;
    }

    public void setMenuids(String menuids) {
        this.menuids = menuids == null ? null : menuids.trim();
    }

    public Date getMealtime() {
        return mealtime;
    }

    public void setMealtime(Date mealtime) {
        this.mealtime = mealtime;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    public Integer getOperationuserid() {
        return operationuserid;
    }

    public void setOperationuserid(Integer operationuserid) {
        this.operationuserid = operationuserid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}