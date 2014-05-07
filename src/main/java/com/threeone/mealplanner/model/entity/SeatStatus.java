package com.threeone.mealplanner.model.entity;

public class SeatStatus {
    private Integer id;

    private Integer seatid;

    private Integer restid;

    private String dateday;

    private Integer dateclock;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDateday() {
        return dateday;
    }

    public void setDateday(String dateday) {
        this.dateday = dateday == null ? null : dateday.trim();
    }

    public Integer getDateclock() {
        return dateclock;
    }

    public void setDateclock(Integer dateclock) {
        this.dateclock = dateclock;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}