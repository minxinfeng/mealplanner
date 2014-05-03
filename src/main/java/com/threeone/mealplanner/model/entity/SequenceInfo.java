package com.threeone.mealplanner.model.entity;

import java.util.Date;

public class SequenceInfo {
    private Integer seqid;

    private String restid;

    private Integer peoplenum;

    private Integer userid;

    private Date seqdate;

    private Integer status;

    public Integer getSeqid() {
        return seqid;
    }

    public void setSeqid(Integer seqid) {
        this.seqid = seqid;
    }

    public String getRestid() {
        return restid;
    }

    public void setRestid(String restid) {
        this.restid = restid == null ? null : restid.trim();
    }

    public Integer getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(Integer peoplenum) {
        this.peoplenum = peoplenum;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getSeqdate() {
        return seqdate;
    }

    public void setSeqdate(Date seqdate) {
        this.seqdate = seqdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}