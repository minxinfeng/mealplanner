package com.threeone.mealplanner.model.entity;

import java.util.Date;

public class SequenceInfo {
    private Integer seqid;

    private Integer restid;
    
    private String restname;

    private Integer userid;

    private Integer peoplenum;

    private Integer seattype;

    private Integer seqno;

    private Date seqdate;

    private Integer status;

    public Integer getSeqid() {
        return seqid;
    }

    public void setSeqid(Integer seqid) {
        this.seqid = seqid;
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

    public Integer getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(Integer peoplenum) {
        this.peoplenum = peoplenum;
    }

    public Integer getSeattype() {
        return seattype;
    }

    public void setSeattype(Integer seattype) {
        this.seattype = seattype;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
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

	public String getRestname() {
		return restname;
	}

	public void setRestname(String restname) {
		this.restname = restname;
	}
}