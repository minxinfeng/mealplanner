package com.threeone.mealplanner.model.entity;

public class MenuInfo {
    private Integer menuid;

    private Integer restid;

    private String menuname;

    private Double menuprice;

    private Integer foodtype;

    private Integer searchtime;

    private Integer ordertime;

    private Integer recommand;

    private Integer hot;

    private String foodurl;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getRestid() {
        return restid;
    }

    public void setRestid(Integer restid) {
        this.restid = restid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Double getMenuprice() {
        return menuprice;
    }

    public void setMenuprice(Double menuprice) {
        this.menuprice = menuprice;
    }

    public Integer getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(Integer foodtype) {
        this.foodtype = foodtype;
    }

    public Integer getSearchtime() {
        return searchtime;
    }

    public void setSearchtime(Integer searchtime) {
        this.searchtime = searchtime;
    }

    public Integer getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Integer ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getRecommand() {
        return recommand;
    }

    public void setRecommand(Integer recommand) {
        this.recommand = recommand;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getFoodurl() {
        return foodurl;
    }

    public void setFoodurl(String foodurl) {
        this.foodurl = foodurl == null ? null : foodurl.trim();
    }
}