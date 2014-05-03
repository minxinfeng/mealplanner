package com.threeone.mealplanner.model.entity;

public class RestType {
    private Integer resttypeid;

    private String resttypename;

    public Integer getResttypeid() {
        return resttypeid;
    }

    public void setResttypeid(Integer resttypeid) {
        this.resttypeid = resttypeid;
    }

    public String getResttypename() {
        return resttypename;
    }

    public void setResttypename(String resttypename) {
        this.resttypename = resttypename == null ? null : resttypename.trim();
    }
}