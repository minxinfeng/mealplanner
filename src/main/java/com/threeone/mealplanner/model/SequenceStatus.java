package com.threeone.mealplanner.model;

/**
 * 排队状态 0：等待，排队中 1：等待结束，用餐 2：取消
 * 
 * @author fengxiangmin
 * 
 */
public enum SequenceStatus {
	waiting(0, "排队中"), eating(1, "用餐中"), cancle(2, "取消");

	private int value;
	private String status;

	private SequenceStatus(int value, String status) {
		this.value = value;
		this.status = status;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
