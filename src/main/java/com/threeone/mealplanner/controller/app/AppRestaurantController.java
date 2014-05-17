package com.threeone.mealplanner.controller.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.RestInfoForMap;
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
	public JsonResult<List<RestaurantWithMenu>> getSeveralRestaurantWithMenus(@RequestParam int start, @RequestParam int limit){
		Boolean flag = true;
		String message = "Get restaurants with menu info from " + start + "  limit to " + limit;
		try {
			List<RestaurantWithMenu> restaurantWithMenus = restaurantService.getSeveralRestaurantWithMenus(start,limit);
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
	
	@RequestMapping("/getRestWithMenuByName")
	@ResponseBody
	public JsonResult<RestaurantWithMenu> getRestWithMenuByName(@RequestParam String restName){
		Boolean flag = true;
		String message = "Get restaurants with menu info of " + restName;
		try {
			RestaurantInfo restaurantInfo = restaurantService.getRestInfoByExactName(restName);
			int restId = restaurantInfo.getRestid();
			RestaurantWithMenu restaurantWithMenu = restaurantService.getRestaurantInfoWithMenu(restId);
			message += " success!";
			return new JsonResult<RestaurantWithMenu>(flag, message, restaurantWithMenu);
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
			return new JsonResult<RestaurantWithMenu>(flag, message, null);
		}
	}
	
	@RequestMapping("/searchByName")
	@ResponseBody
	public JsonResult<List<RestaurantInfo>> searchByName(@RequestParam String restName){
		Boolean flag = true;
		String message = "Get restaurants info of " + restName;
		try {
			List<RestaurantInfo> restaurantInfos = restaurantService.getRestsByName(restName);
			message += " success!";
			return new JsonResult<List<RestaurantInfo>>(flag, message, restaurantInfos);
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
			return new JsonResult<List<RestaurantInfo>>(flag, message, null);
		}
	}
	
	@RequestMapping("/getRestInfoForMaps")
	@ResponseBody
	public JsonResult<List<RestInfoForMap>> getRestInfoForMaps(@RequestParam String restNames){
		Boolean flag = true;
		String message = "Get restaurants info for maps of " + restNames;
		List<RestInfoForMap> restInfoForMaps = new ArrayList<RestInfoForMap>();
		try {
			restInfoForMaps = restaurantService.getRestInfoForMaps(restNames);
			message += " success!";
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
		}
		return new JsonResult<List<RestInfoForMap>>(flag, message, restInfoForMaps);
	}
	
	@RequestMapping("/insertRestByMap")
	@ResponseBody
	public JsonResult<String> insertRestByMap(@RequestParam String restName, @RequestParam String restAddress, @RequestParam int restType, @RequestParam(defaultValue="0") double longitude, @RequestParam(defaultValue="0") double latitude){
		Boolean flag = true;
		String message = "Insert restInfo by map: restName=" + restName + ", restAddress=" + restAddress + ", longitude=" + longitude + ", latitude=" + latitude;
		try {
			RestaurantInfo restaurantInfo = new RestaurantInfo();
			restaurantInfo.setLatitude(latitude);
			restaurantInfo.setLongitude(longitude);
			restaurantInfo.setRestaddress(restAddress);
			restaurantInfo.setRestcity(1);
			restaurantInfo.setRestname(restName);
			restaurantInfo.setResttype(restType);
			restaurantService.registRestaurant(restaurantInfo);
			message += " success!";
		} catch (Exception e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
	@RequestMapping("/getSeveralRest")
	@ResponseBody
	public JsonResult<List<RestaurantInfo>> getSeveralRest(@RequestParam int start, @RequestParam int limit){
		Boolean flag = true;
		String message = "Get restaurants info from " + start + "  limit to " + limit;
		try {
			List<RestaurantInfo> restaurantInfos = restaurantService.getSeveralRest(start,limit);
			message += " success!";
			return new JsonResult<List<RestaurantInfo>>(flag, message, restaurantInfos);
		} catch (InternalException e) {
			flag = false;
			message = message + " failed! Reason:" + e.getMessage();
			return new JsonResult<List<RestaurantInfo>>(flag, message, null);
		}
	}

}
