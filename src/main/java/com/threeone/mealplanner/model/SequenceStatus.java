package com.threeone.mealplanner.model;

/**
 * �Ŷ�״̬ 0���ȴ����Ŷ��� 1���ȴ��������ò� 2��ȡ��
 * 
 * @author fengxiangmin
 * 
 */
public enum SequenceStatus {
	waiting(0, "�Ŷ���"), eating(1, "�ò���"), cancle(2, "ȡ��");

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
