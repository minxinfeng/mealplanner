package com.threeone.mealplanner.model;

/**
 * �����еĺ�����Ϣ���������״̬
 * @author asus
 *
 */
public class MealFriendWithStatus {
	private UserInfoForShow friendInfo;
	private int status;
	
	public UserInfoForShow getFriendInfo() {
		return friendInfo;
	}
	public void setFriendInfo(UserInfoForShow friendInfo) {
		this.friendInfo = friendInfo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
