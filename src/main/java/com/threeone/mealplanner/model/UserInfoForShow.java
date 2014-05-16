package com.threeone.mealplanner.model;

/**
 * 显示在给用户的一系列信息
 * @author fengxiangmin
 *
 */
public class UserInfoForShow {
	private Integer userid;

    private String username;

    private String email;

    private String phonenum;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
    
    
}
