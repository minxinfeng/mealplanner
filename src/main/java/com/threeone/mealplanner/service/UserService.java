package com.threeone.mealplanner.service;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.entity.UserBind;
import com.threeone.mealplanner.model.entity.UserInfo;

public interface UserService {
	
	UserInfo getUserInfoById(int id);
	
	/**
	 * 登录
	 * @param loginName  登录名，可以使username,phonenum,email
	 * @return
	 */
	UserInfo getUserInfoByLogin(String loginName);
	
	/**
	 * 注册
	 * @param userInfo
	 * @throws InternalException
	 */
	void register(UserInfo userInfo) throws InternalException;
	
	/**
	 * 将用户账号绑定到百度推送中
	 * @param userBind
	 * @throws InternalException
	 */
	void addUserChannel(UserBind userBind) throws InternalException;
	
}
