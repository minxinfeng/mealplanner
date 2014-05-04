package com.threeone.mealplanner.service;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.entity.UserInfo;

public interface UserService {
	
	UserInfo getUserInfoById(int id);
	
	/**
	 * ��¼
	 * @param loginName  ��¼��������ʹusername,phonenum,email
	 * @param password
	 * @return
	 */
	UserInfo getUserInfoByLogin(String loginName, String password);
	
	/**
	 * ע��
	 * @param userInfo
	 * @throws InternalException
	 */
	void register(UserInfo userInfo) throws InternalException;
	
}
