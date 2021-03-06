package com.threeone.mealplanner.controller.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.common.Message;
import com.threeone.mealplanner.model.entity.RestCity;
import com.threeone.mealplanner.model.entity.RestType;
import com.threeone.mealplanner.model.entity.RestUser;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.model.entity.UserType;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.RestaurantTypeService;
import com.threeone.mealplanner.service.UserService;

@Controller
@RequestMapping("/web")
public class WebUserInfoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantTypeService restaurantTypeService;
	
	@RequestMapping("/home")
	public String index(Model model) {
		return "home.ftl";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(){
		return "auth/login.ftl";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(@RequestParam String loginName, @RequestParam String password, Model model, HttpSession session){		
		try {
			UserInfo userInfo = userService.getUserInfoByLogin(loginName);
			RestaurantInfo restaurantInfo = restaurantService.getRestNameByUser(userInfo);
			Message message = new Message();
			session.setAttribute("userId", userInfo.getUserid());
			if(userInfo == null || !userInfo.getPassword().equals(password)){
				message.danger("Username or password is error!");
				model.addAttribute("messages", message.getMessages());
				return "auth/login.ftl";
			}else{			
				message.success("login success!");
				model.addAttribute("userInfo", userInfo);
				model.addAttribute("restaurantInfo", restaurantInfo);
				model.addAttribute("messages", message.getMessages());
				return "index.ftl";
			}
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "auth/login.ftl";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet(Model model){		
		List<RestType> restTypes;
		List<RestCity> restCites;
		try {
			restTypes = restaurantTypeService.getAllType();
			restCites = restaurantService.getAllCity();
			model.addAttribute("restTypes", restTypes);
			model.addAttribute("restCites", restCites);
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "auth/register.ftl";
	}
	

	@RequestMapping(value="/getRestauntId", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> getRestauntIByName(@RequestParam String restName){
		Boolean flag = true;
		String message = "Get restaurant ID success!";
		try {
			String restId = restaurantService.getRestsByName(restName).get(0).getRestid().toString();
			return new JsonResult<String>(flag, message, restId);
		} catch (InternalException e) {
			flag = false;
			message = "Get restaurant ID failed! Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping(value="/getUserId", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> getUserIdByName(@RequestParam String userName){
		Boolean flag = true;
		String message = "Get restaurant ID success!";
		String userId;
		try {
			userId = userService.getUserInfoByLogin(userName).getUserid().toString();
			System.out.println("userId:"+ userId);
			return new JsonResult<String>(flag, message, userId);	
		} catch (Exception e) {
			flag = false;
			message = "Get restaurant ID failed! Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
			
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerPost(@RequestParam String username, @RequestParam String phonenum, @RequestParam String email, @RequestParam String password, 
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
		restaurantInfo.setResttype(restType);
		RestUser restUser = new RestUser();
		Message message = new Message();		
		if(!"".equals(restWebsite)){
			restaurantInfo.setRestwebsite(restWebsite);
		}	
		try {
			userService.register(userInfo);
			message.success("username " + userInfo.getUsername() + " register success!");
			model.addAttribute("messages", message.getMessages());
			restaurantService.registRestaurant(restaurantInfo);
			restUser.setRestid(restaurantService.getRestsByName(restName).get(0).getRestid());
			restUser.setUserid(userService.getUserInfoByLogin(username).getUserid());
			restaurantService.mapRestaurantUser(restUser);
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
