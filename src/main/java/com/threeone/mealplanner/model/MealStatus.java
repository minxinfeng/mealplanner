package com.threeone.mealplanner.model;

/**
 * ����״̬
 * 0�����ڽ���
 * 1���ɹ������¶���
 * 2������
 * @author asus
 *
 */
public enum MealStatus {
	ongoing(0,"���ڽ���"),
	success(1,"�ɹ������¶���"),
	expire(2,"����");
	
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
