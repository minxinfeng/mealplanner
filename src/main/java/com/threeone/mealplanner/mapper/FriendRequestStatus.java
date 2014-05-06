package com.threeone.mealplanner.mapper;

public enum FriendRequestStatus {
	requesting(0,"�ȴ�������"),
	accept(1,"ͬ��"),
	reject(2,"�ܾ�");
	
	private int value;
	private String handleString;
	
	private FriendRequestStatus(int value, String handleString) {
		this.value = value;
		this.handleString = handleString;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getHandleString() {
		return handleString;
	}

	public void setHandleString(String handleString) {
		this.handleString = handleString;
	}
}
