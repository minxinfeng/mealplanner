package com.threeone.mealplanner.model;

import com.threeone.mealplanner.model.entity.UserInfo;

/**
 * 饭局中的好友信息和相关邀请状态
 * @author asus
 *
 */
public class MealFriendWithStatus {
	private UserInfo friendInfo;
	private int status;
	
	public UserInfo getFriendInfo() {
		return friendInfo;
	}
	public void setFriendInfo(UserInfo friendInfo) {
		this.friendInfo = friendInfo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
