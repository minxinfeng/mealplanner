package com.threeone.mealplanner.service.impl;

import com.threeone.mealplanner.common.InternalException;
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

	@Override
	public UserInfo getUserInfoByLogin(String loginName, String password) {
		UserInfo userInfo = userInfoMapper.getUserInfoByLogin(loginName, password);
		return userInfo;
	}
	
	@Override
	public void register(UserInfo userInfo) throws InternalException {
		//判断用户填写信息是否已经被注册了
		UserInfo userInfoByName = userInfoMapper.getUserInfoForCheck(userInfo.getUsername(), null, null);
		if(userInfoByName != null){
			throw new InternalException("Username exists, please verify!");
		}
		
		UserInfo userInfoByPhoneNum = userInfoMapper.getUserInfoForCheck(null, userInfo.getPhonenum(), null);
		if(userInfoByPhoneNum != null){
			throw new InternalException("Phone Number exists, please verify!");
		}
		
		UserInfo userInfoByEmail = userInfoMapper.getUserInfoForCheck(null, null, userInfo.getEmail());
		if(userInfoByEmail != null){
			throw new InternalException("Email exists, please verify!");
		}
		
		//不存在，则正常注册
		try {
			int ninserts = userInfoMapper.insertSelective(userInfo);
			if (ninserts == 0) {
				throw new InternalException("Register error! ");
			}
		} catch (Exception e) {
			throw new InternalException("Register error! Error message:" + e.getMessage());
		}
		
	}
	
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	
	

}
