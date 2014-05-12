package com.threeone.mealplanner.model.entity;

public class UserBind {
    private Integer id;

    private Integer userid;

    private String baiduuserid;

    private Long channelid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBaiduuserid() {
        return baiduuserid;
    }

    public void setBaiduuserid(String baiduuserid) {
        this.baiduuserid = baiduuserid == null ? null : baiduuserid.trim();
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }
}