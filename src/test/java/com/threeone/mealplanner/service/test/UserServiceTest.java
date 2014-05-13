package com.threeone.mealplanner.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.UserService;
import com.threeone.mealplanner.test.common.AbstractSpringCommonTest;

public class UserServiceTest extends AbstractSpringCommonTest{

	@Autowired
	private UserService userService;

	@Test
	public void test(){
		UserInfo userInfo = userService.getUserInfoByLogin("fxm");
		System.out.println(userInfo.getEmail());
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}