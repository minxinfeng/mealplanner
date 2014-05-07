package com.threeone.mealplanner.model;

/**
 * 饭局中被邀请的好友的反馈状态
 *	0：待接受
 *	1：已接受
 *	2：已拒绝
 * @author fengxiangmin
 *
 */
public enum MealFriendStatus {
	waiting(0,"等待处理中"),
	accept(1,"已接受"),
	reject(2,"已拒绝");
	
	private int value;
	private String name;
	
	private MealFriendStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
