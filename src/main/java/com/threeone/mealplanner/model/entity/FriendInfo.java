package com.threeone.mealplanner.model.entity;

public class FriendInfo {
    private Integer id;

    private Integer userid;

    private Integer friendid;

    private Integer status;

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

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}