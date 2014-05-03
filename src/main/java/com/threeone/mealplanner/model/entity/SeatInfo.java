package com.threeone.mealplanner.model.entity;

public class SeatInfo {
    private Integer seatid;

    private Integer restid;

    private Integer seatno;

    private Integer peoplenum;

    private String description;

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    public Integer getRestid() {
        return restid;
    }

    public void setRestid(Integer restid) {
        this.restid = restid;
    }

    public Integer getSeatno() {
        return seatno;
    }

    public void setSeatno(Integer seatno) {
        this.seatno = seatno;
    }

    public Integer getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(Integer peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}