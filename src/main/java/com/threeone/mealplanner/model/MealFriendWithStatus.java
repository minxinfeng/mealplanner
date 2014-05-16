package com.threeone.mealplanner.model;

/**
 * 饭局中的好友信息和相关邀请状态
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
