package com.threeone.mealplanner.model;

/**
 * �����б�����ĺ��ѵķ���״̬
 *	0��������
 *	1���ѽ���
 *	2���Ѿܾ�
 * @author fengxiangmin
 *
 */
public enum MealFriendStatus {
	waiting(0,"�ȴ�������"),
	accept(1,"�ѽ���"),
	reject(2,"�Ѿܾ�");
	
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
