package com.threeone.mealplanner.service.impl;

import com.threeone.mealplanner.mapper.UserInfoMapper;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.UserService;

public class UserServiceImpl implements UserService {

	private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo getUserInfoById(int id) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		return userInfo;
	}

	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}
	

}
