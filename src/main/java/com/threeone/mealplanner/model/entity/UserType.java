package com.threeone.mealplanner.model.entity;

/**
 * �û����ͣ�1��ϵͳ����Ա��2�ǲ�������Ա��3����ͨ�û�
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
