package com.threeone.mealplanner.controller.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.MealFriendStatus;
import com.threeone.mealplanner.model.MealStatus;
import com.threeone.mealplanner.model.MealWithFriends;
import com.threeone.mealplanner.model.entity.MealInfo;
import com.threeone.mealplanner.service.MealService;

@Controller
@RequestMapping("/app/meal")
public class AppMealController {
	
	@Autowired
	private MealService mealService;
	
	@RequestMapping("/createMeal")
	@ResponseBody
	public JsonResult<String> createMeal(@RequestParam int restId, @RequestParam String datetime, @RequestParam int userId, @RequestParam String friendIds){
		Boolean flag = false;
		String message = "Create Meal ";
		try {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = format1.parse(datetime); 
			MealInfo mealInfo = new MealInfo();
			mealInfo.setMealorganizeuserid(userId);
			mealInfo.setMealstatus(MealStatus.ongoing.getValue());
			mealInfo.setOrganizationtime(new Date());
			mealInfo.setMealtime(date);
			mealInfo.setRestid(restId);
			mealService.createMeal(mealInfo, friendIds);
			message += " success!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed! reason:" + e.getMessage();
		}
		
		return new JsonResult<String>(flag, message, null);
	}
	
	@RequestMapping("/getMealInfo")
	@ResponseBody
	public JsonResult<List<MealWithFriends>> getMealInfoByUserId(@RequestParam int userId, @RequestParam int status){
		Boolean flag = false;
		String message = "Get Meal of userId=" + userId + " and status=" + status;
		try {
			List<MealWithFriends> mealWithFriends = mealService.getMealInfoByUserId(userId, status);
			message += " success!";
			flag = true;
			return new JsonResult<List<MealWithFriends>>(flag, message, mealWithFriends);
		} catch (Exception e) {
			message = message + "failed! reason:" + e.getMessage();
			return new JsonResult<List<MealWithFriends>>(flag, message, null);
		}
	}
	
	@RequestMapping("/getMealRequest")
	@ResponseBody
	public JsonResult<List<MealInfo>> getMealRequest(@RequestParam int userId, @RequestParam int status){
		Boolean flag = false;
		String message = "Get Meal request of userId=" + userId + " and status=" + status;
		try {
			List<MealInfo> mealInfos = mealService.getMealRequest(userId, status);
			message += " success!";
			flag = true;
			return new JsonResult<List<MealInfo>>(flag, message, mealInfos);
		} catch (Exception e) {
			message = message + "failed! reason:" + e.getMessage();
			return new JsonResult<List<MealInfo>>(flag, message, null);
		}
	}
	
	@RequestMapping("/acceptMeal")
	@ResponseBody
	public JsonResult<String> acceptMeal(@RequestParam int mealId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId=" + userId + " accept Meal request of  mealId= " + mealId;
		try {
			mealService.handleAMeal(mealId, userId, MealFriendStatus.accept.getValue());
			message += " success!";
			flag = true;
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + "failed! reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping("/rejectMeal")
	@ResponseBody
	public JsonResult<String> rejectMeal(@RequestParam int mealId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId=" + userId + " reject Meal request of  mealId= " + mealId;
		try {
			mealService.handleAMeal(mealId, userId, MealFriendStatus.reject.getValue());
			message += " success!";
			flag = true;
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + "failed! reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping("/mealFriendInfo")
	@ResponseBody
	public JsonResult<MealWithFriends> mealFriendInfo(@RequestParam int mealId){
		Boolean flag = false;
		String message = " Get Meal Friends info of  mealId= " + mealId;
		try {
			MealWithFriends mealWithFriends = mealService.getMealInfoFriends(mealId);
			message += " success!";
			flag = true;
			return new JsonResult<MealWithFriends>(flag, message, mealWithFriends);
		} catch (Exception e) {
			message = message + "failed! reason:" + e.getMessage();
			return new JsonResult<MealWithFriends>(flag, message, null);
		}
	}

}