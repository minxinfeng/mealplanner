package com.threeone.mealplanner.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.UserInfoMapper;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.UserService;

public class UserServiceImpl implements UserService {

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	
	private UserInfoMapper userInfoMapper;
	
	
	public UserInfo getUserInfoById(int id) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		return userInfo;
	}

	
	public UserInfo getUserInfoByLogin(String loginName) {
		UserInfo userInfo = userInfoMapper.getUserInfoByLogin(loginName);
		return userInfo;
	}
	
	
	public void register(UserInfo userInfo) throws InternalException {
		//判断用户填写信息是否已经被注册了
		UserInfo userInfoByName = userInfoMapper.getUserInfoForCheck(userInfo.getUsername(), null, null);
		if(userInfoByName != null){
			LOG.error("Username exists, please verify!");
			throw new InternalException("Username exists, please verify!");
		}
		
		UserInfo userInfoByPhoneNum = userInfoMapper.getUserInfoForCheck(null, userInfo.getPhonenum(), null);
		if(userInfoByPhoneNum != null){
			LOG.error("Phone Number exists, please verify!");
			throw new InternalException("Phone Number exists, please verify!");
		}
		
		UserInfo userInfoByEmail = userInfoMapper.getUserInfoForCheck(null, null, userInfo.getEmail());
		if(userInfoByEmail != null){
			LOG.error("Email exists, please verify!");
			throw new InternalException("Email exists, please verify!");
		}
		
		//不存在，则正常注册
		try {
			int ninserts = userInfoMapper.insertSelective(userInfo);
			if (ninserts == 0) {
				LOG.error("Register error!");
				throw new InternalException("Register error! ");
			}
			LOG.info("register success!");
		} catch (Exception e) {
			LOG.error("Register error! Error message:" + e.getMessage());
			throw new InternalException("Register error! Error message:" + e.getMessage());
		}
		
	}
	
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}	

}
