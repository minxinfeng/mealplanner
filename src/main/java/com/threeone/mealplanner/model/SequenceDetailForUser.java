package com.threeone.mealplanner.model;

public class SequenceDetailForUser {
	private int userId;//�û�ID
	private int restId;//����ID
	private int seqNo;// �ŶӺ�ID
	private int seqNow;//�������ڽ��е��ŶӺ�ID
	private int peopleBefore;// ǰ���ŶӵĶ���
	private int peopleNum;//���Ŷ��е�����

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
}
