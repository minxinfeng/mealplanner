package com.threeone.mealplanner.model.entity;

public class RestaurantInfo {
    private Integer restid;

    private String restname;

    private String restphone;

    private Integer restcity;
    
    private String restcityname;

    private String restaddress;

    private String restwebsite;

    private Integer resttype;
    
    private String restTypeName;

    private Integer restsearchtime;

    private Integer hot;

    private Double longitude;

    private Double latitude;

    public Integer getRestid() {
        return restid;
    }

    public void setRestid(Integer restid) {
        this.restid = restid;
    }

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname == null ? null : restname.trim();
    }

    public String getRestphone() {
        return restphone;
    }

    public void setRestphone(String restphone) {
        this.restphone = restphone == null ? null : restphone.trim();
    }

    public Integer getRestcity() {
        return restcity;
    }

    public void setRestcity(Integer restcity) {
        this.restcity = restcity;
    }

    public String getRestaddress() {
        return restaddress;
    }

    public void setRestaddress(String restaddress) {
        this.restaddress = restaddress == null ? null : restaddress.trim();
    }

    public String getRestwebsite() {
        return restwebsite;
    }

    public void setRestwebsite(String restwebsite) {
        this.restwebsite = restwebsite == null ? null : restwebsite.trim();
    }

    public Integer getResttype() {
        return resttype;
    }

    public void setResttype(Integer resttype) {
        this.resttype = resttype;
    }

    public Integer getRestsearchtime() {
        return restsearchtime;
    }

    public void setRestsearchtime(Integer restsearchtime) {
        this.restsearchtime = restsearchtime;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

	public String getRestcityname() {
		return restcityname;
	}

	public void setRestcityname(String restcityname) {
		this.restcityname = restcityname;
	}

	public String getRestTypeName() {
		return restTypeName;
	}

	public void setRestTypeName(String restTypeName) {
		this.restTypeName = restTypeName;
	}
}