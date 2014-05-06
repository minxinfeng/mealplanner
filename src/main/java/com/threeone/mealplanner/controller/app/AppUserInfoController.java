package com.threeone.mealplanner.controller.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.model.entity.UserType;
import com.threeone.mealplanner.service.UserService;

@Controller
@RequestMapping("/app")
public class AppUserInfoController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param loginName 登录名，可以使username,phonenum,email
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult<UserInfo> login(@RequestParam String loginName, @RequestParam String password){
		UserInfo userInfo = userService.getUserInfoByLogin(loginName);
		Boolean flag = true;
		String message = "login success!";
		if(userInfo == null || !userInfo.getPassword().equals(password)){
			flag = false;
			message = "Username or password is error!";
		}
		return new JsonResult<UserInfo>(flag, message, userInfo);
	}
	
	@RequestMapping(value="/register")
	@ResponseBody
	public JsonResult<UserInfo> register(@RequestParam String username, @RequestParam String phonenum, @RequestParam String email, @RequestParam String password){
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setEmail(email);
		userInfo.setPassword(password);
		userInfo.setPhonenum(phonenum);
		userInfo.setUsertype(UserType.appUser.getUserType());
		userInfo.setRegisterdate(new Date());
		String message;
		Boolean flag = true;
		try {
			userService.register(userInfo);
			userInfo.setUserid(userService.getUserInfoByLogin(phonenum).getUserid());
			message = "Register success!";
		} catch (InternalException e) {
			message = "Register error, error message:" + e.getMessage();
			flag = false;
			userInfo = null;
		}
		return new JsonResult<UserInfo>(flag, message, userInfo); 
	}
	
	
	@RequestMapping("/userInfo")
	@ResponseBody
	public JsonResult<UserInfo> getUserinfo(@RequestParam(defaultValue="1") int userId, Model model) {
		UserInfo userInfo = userService.getUserInfoById(userId);
		Boolean flag = true;
		String message = "success";
		
		return new JsonResult<UserInfo>(flag, message, userInfo);
	}

}
