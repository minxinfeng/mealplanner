package com.threeone.mealplanner.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.entity.FoodType;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.service.MenuService;

@Controller
@RequestMapping("/app/menu")
public class AppMenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/getMenuByRestId")
	@ResponseBody
	public JsonResult<List<MenuInfo>> getMenuInfoByRestId(@RequestParam int restId){
		Boolean flag = true;
		String message = "Get menu info of the restId=" + restId;
		try {
			List<MenuInfo> menuInfos = menuService.getMenuInfoByRestId(restId);
			message += " success!";
			return new JsonResult<List<MenuInfo>>(flag, message, menuInfos);
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return new JsonResult<List<MenuInfo>>(flag, message, null);
		}
	}
	
	@RequestMapping("/getMenuByFoodType")
	@ResponseBody
	public JsonResult<List<MenuInfo>> getMenuByFoodType(@RequestParam int foodTypeId){
		Boolean flag = true;
		String message = "Get menu info of the foodTypeId=" + foodTypeId;
		try {
			List<MenuInfo> menuInfos = menuService.getMenuInfoByType(foodTypeId);
			message += " success!";
			return new JsonResult<List<MenuInfo>>(flag, message, menuInfos);
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return new JsonResult<List<MenuInfo>>(flag, message, null);
		}
	}
	
	@RequestMapping("/getAllFoodType")
	@ResponseBody
	public JsonResult<List<FoodType>> getAllFoodType(){
		Boolean flag = true;
		String message = "Get all foodType success!";
		try {
			List<FoodType> foodTypes = menuService.getAllFoodTypes();
			return new JsonResult<List<FoodType>>(flag, message, foodTypes);
		} catch (Exception e) {
			flag = false;
			message = "Get all foodType error! Reason:" + e.getMessage();
			return new JsonResult<List<FoodType>>(flag, message, null);
		}
	}
	
}
