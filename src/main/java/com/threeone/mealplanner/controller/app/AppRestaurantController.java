package com.threeone.mealplanner.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.service.RestaurantService;

@Controller
@RequestMapping("/app/rest")
public class AppRestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping("/getAllRest")
	@ResponseBody
	public JsonResult<List<RestaurantWithMenu>> getAllRestaurants(){
		Boolean flag = true;
		String message = "Get all restaurants success!";
		try {
			List<RestaurantWithMenu> restaurantWithMenus = restaurantService.getAllRestaurantWithMenus();
			return new JsonResult<List<RestaurantWithMenu>>(flag, message, restaurantWithMenus);
		} catch (InternalException e) {
			flag = false;
			message = "Get all restaurants failed! Reason:" + e.getMessage();
			return new JsonResult<List<RestaurantWithMenu>>(flag, message, null);
		}
		
	}

}
