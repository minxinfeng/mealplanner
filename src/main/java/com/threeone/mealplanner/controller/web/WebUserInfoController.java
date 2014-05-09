package com.threeone.mealplanner.controller.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.Message;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.model.entity.UserType;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.UserService;

@Controller
@RequestMapping("/web")
public class WebUserInfoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(){
		return "auth/login.ftl";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(@RequestParam String loginName, @RequestParam String password, Model model){
		UserInfo userInfo = userService.getUserInfoByLogin(loginName);
		Message message = new Message();
		if(userInfo == null || !userInfo.getPassword().equals(password)){
			message.danger("Username or password is error!");
			model.addAttribute("messages", message.getMessages());
			return "auth/login.ftl";
		}else{
			message.success("login success!");
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("messages", message.getMessages());
			return "index.ftl";
		}
		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet(){
		return "auth/register.ftl";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerForRestPost(@RequestParam String username, @RequestParam String phonenum, @RequestParam String email, @RequestParam String password, 
			@RequestParam String restName, @RequestParam String restAddress, @RequestParam int restCity, @RequestParam String restWebsite, @RequestParam int restType, Model model){
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setEmail(email);
		userInfo.setPassword(password);
		userInfo.setPhonenum(phonenum);
		userInfo.setUsertype(UserType.restAdmin.getUserType());
		userInfo.setRegisterdate(new Date());
		RestaurantInfo restaurantInfo = new RestaurantInfo();
		restaurantInfo.setRestname(restName);
		restaurantInfo.setRestaddress(restAddress);
		restaurantInfo.setRestcity(restCity);
		if(!"".equals(restWebsite)){
			restaurantInfo.setRestwebsite(restWebsite);
		}			
		Message message = new Message();
		try {
			userService.register(userInfo);
			message.success("username " + userInfo.getUsername() + " register success!");
			model.addAttribute("messages", message.getMessages());
			restaurantService.registRestaurant(restaurantInfo);
			message.success("restname " + restaurantInfo.getRestname() + " register success!");
			model.addAttribute("messages", message.getMessages());
			return "auth/login.ftl";
		} catch (InternalException e) {
			message.danger("Register error, error message:" + e.getMessage());
			model.addAttribute("messages", message.getMessages());
			return "auth/register.ftl";
		}
	}

}
