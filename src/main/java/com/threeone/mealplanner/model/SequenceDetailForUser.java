package com.threeone.mealplanner.model;

public class SequenceDetailForUser {
	private int seqId; //排队唯一标识ID
	private int userId;//用户ID
	private int restId;//餐厅ID
	private String restName;//餐厅名称
	private int seqNo;// 排队号ID
	private int seqNow;//现在正在进行的排队号ID
	private int seatType;//几人桌
	private int peopleBefore;// 前面排队的队数
	private int peopleNum;//该排队中的人数

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getPeopleBefore() {
		return peopleBefore;
	}

	public void setPeopleBefore(int peopleBefore) {
		this.peopleBefore = peopleBefore;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public int getSeqNow() {
		return seqNow;
	}

	public void setSeqNow(int seqNow) {
		this.seqNow = seqNow;
	}

	public int getSeatType() {
		return seatType;
	}

	public void setSeatType(int seatType) {
		this.seatType = seatType;
	}

	public int getSeqId() {
		return seqId;
	}

	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}
}
