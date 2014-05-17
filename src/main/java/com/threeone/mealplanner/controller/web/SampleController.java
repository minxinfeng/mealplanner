package com.threeone.mealplanner.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.UserService;

@Controller
public class SampleController {

	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping("/index")
	public String index(@RequestParam int userId, Model model) throws InternalException {
		UserInfo userInfo = userService.getUserInfoById(userId);
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			RestaurantInfo restaurantInfo = restaurantService.getRestaurantInfo(restId);
			model.addAttribute("restaurantInfo", restaurantInfo);
			model.addAttribute("userinfo", userInfo);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return "index.ftl";
	}
	
	@RequestMapping("/userinfo")
	@ResponseBody
	public JsonResult<UserInfo> getUserinfo(@RequestParam(defaultValue="1") int userId, Model model) {
		UserInfo userInfo = userService.getUserInfoById(userId);
		Boolean flag = true;
		String message = "success";
		
		return new JsonResult<UserInfo>(flag, message, userInfo);
	}
	
	@Autowired
	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
