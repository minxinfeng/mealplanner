package com.threeone.mealplanner.service;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.entity.UserInfo;

public interface UserService {
	
	UserInfo getUserInfoById(int id);
	
	/**
	 * 登录
	 * @param loginName  登录名，可以使username,phonenum,email
	 * @param password
	 * @return
	 */
	UserInfo getUserInfoByLogin(String loginName, String password);
	
	/**
	 * 注册
	 * @param userInfo
	 * @throws InternalException
	 */
	void register(UserInfo userInfo) throws InternalException;
	
}
