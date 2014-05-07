package com.threeone.mealplanner.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
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
	
	@RequestMapping("/getSeveralRestWithMenu")
	@ResponseBody
	public JsonResult<List<RestaurantWithMenu>> getSeveralRestaurantWithMenus(@RequestParam int start, int end){
		Boolean flag = true;
		String message = "Get restaurants info from " + start + " to " + end;
		try {
			List<RestaurantWithMenu> restaurantWithMenus = restaurantService.getSeveralRestaurantWithMenus(start,end);
			message += " success!";
			return new JsonResult<List<RestaurantWithMenu>>(flag, message, restaurantWithMenus);
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
			return new JsonResult<List<RestaurantWithMenu>>(flag, message, null);
		}
	}
	
	@RequestMapping("/getRest")
	@ResponseBody
	public JsonResult<RestaurantInfo> getRest(@RequestParam int restId){
		Boolean flag = true;
		String message = "Get restaurants info of " + restId;
		try {
			RestaurantInfo restaurantInfo = restaurantService.getRestaurantInfo(restId);
			message += " success!";
			return new JsonResult<RestaurantInfo>(flag, message, restaurantInfo);
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
			return new JsonResult<RestaurantInfo>(flag, message, null);
		}
	}
	
	@RequestMapping("/getRestWithMenu")
	@ResponseBody
	public JsonResult<RestaurantWithMenu> getRestWithMenu(@RequestParam int restId){
		Boolean flag = true;
		String message = "Get restaurants info of " + restId;
		try {
			RestaurantWithMenu restaurantWithMenu = restaurantService.getRestaurantInfoWithMenu(restId);
			message += " success!";
			return new JsonResult<RestaurantWithMenu>(flag, message, restaurantWithMenu);
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
			return new JsonResult<RestaurantWithMenu>(flag, message, null);
		}
	}

}
