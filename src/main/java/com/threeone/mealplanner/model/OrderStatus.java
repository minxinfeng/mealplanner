package com.threeone.mealplanner.model;

/**
 * ����״̬
 * @author fengxiangmin
 * ����״̬��-1�������ж�  0�������ύ�ɹ� 1�������ύʧ��(����λ) 2���Ѿ����ͨ�� 3����ȡ�� 4�������
 *
 */
public enum OrderStatus {
	all(-1, "all"),
	commitSuccess(0, "commit success"),
	commitFailed(1, "commit failed"),
	comfirmed(2, "restaurant confirmed"),
	cancled(3, "cancled"),
	finished(4, "finished");
	
	private int value;
	private String statusString;
	private OrderStatus(int value, String statusString) {
		this.value = value;
		this.statusString = statusString;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
	

}
