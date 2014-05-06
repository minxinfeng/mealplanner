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
	ongoing(0,"正在进行"),
	success(1,"成功，已下订单"),
	expire(2,"过期");
	
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
