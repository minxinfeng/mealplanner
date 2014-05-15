package com.threeone.mealplanner.model.entity;

import java.util.Date;

public class MealInfo {
    private Integer mealid;

    private Integer mealorganizeuserid;
    
    private String mealorganizeusername;

    private Integer restid;
    
    private String restname;

    private Date mealtime;

    private Date organizationtime;

    private Integer mealstatus;

    public Integer getMealid() {
        return mealid;
    }

    public void setMealid(Integer mealid) {
        this.mealid = mealid;
    }

    public Integer getMealorganizeuserid() {
        return mealorganizeuserid;
    }

    public void setMealorganizeuserid(Integer mealorganizeuserid) {
        this.mealorganizeuserid = mealorganizeuserid;
    }

    public Integer getRestid() {
        return restid;
    }

    public void setRestid(Integer restid) {
        this.restid = restid;
    }

    public Date getMealtime() {
        return mealtime;
    }

    public void setMealtime(Date mealtime) {
        this.mealtime = mealtime;
    }

    public Date getOrganizationtime() {
        return organizationtime;
    }

    public void setOrganizationtime(Date organizationtime) {
        this.organizationtime = organizationtime;
    }

	public Integer getMealstatus() {
		return mealstatus;
	}

	public void setMealstatus(Integer mealstatus) {
		this.mealstatus = mealstatus;
	}

	public String getMealorganizeusername() {
		return mealorganizeusername;
	}

	public void setMealorganizeusername(String mealorganizeusername) {
		this.mealorganizeusername = mealorganizeusername;
	}

	public String getRestname() {
		return restname;
	}

	public void setRestname(String restname) {
		this.restname = restname;
	}

}