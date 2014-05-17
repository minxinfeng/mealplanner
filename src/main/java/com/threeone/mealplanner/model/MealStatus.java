package com.threeone.mealplanner.model;


/**
 * 饭局状态
 * 0：正在进行
 * 1：成功，已下订单
 * 2：过期
 * @author asus
 *
 */
public enum MealStatus {
	all(-1,"所有"),
	ongoing(0,"正在进行"),
	success(1,"成功，所有人已经同意"),
	reject(2,"所有人已回馈，但是有人拒绝");
	
	private int value;
	private String name;
	
	private MealStatus(int value, String name) {
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
