package com.threeone.mealplanner.model.entity;

/**
 * 用户类型：1是系统管理员，2是餐厅管理员，3是普通用户
 * @author asus
 *
 */
public enum UserType {
	admin(1),
	restAdmin(2),
	appUser(3);
	
	private int userType;

	private UserType(int userType) {
		this.userType = userType;
	}

	public int getUserType() {
		return userType;
	}

}
