package com.threeone.mealplanner.model;

import java.util.List;

import com.threeone.mealplanner.model.entity.UserInfo;

public class UserInfoWithFriends {
	
	private int userId;
	private int status;
	private List<UserInfo> userInfos;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<UserInfo> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	
	

}
