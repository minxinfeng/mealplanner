package com.threeone.mealplanner.model;

/**
 * 订单状态
 * @author fengxiangmin
 * 订单状态：-1则是所有订  0：订单提交成功 1：订单提交失败(无座位) 2：已经审核通过 3：被取消 4：已完成
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
