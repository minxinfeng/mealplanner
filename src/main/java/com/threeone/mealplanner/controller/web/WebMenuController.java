package com.threeone.mealplanner.controller.web;

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

/**
 * Need to update for the web side
 * @author asus
 *
 */
@Controller
@RequestMapping("/web/menu")
public class WebMenuController {
	
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
	
	@RequestMapping("/addMenu")
	@ResponseBody
	public JsonResult<String> addMenu(@RequestParam int restid, @RequestParam String menuname, 
			@RequestParam Double menuprice, @RequestParam Integer foodtype, @RequestParam Integer recommand, @RequestParam Integer hot){
		Boolean flag = true;
		String message = "Add menu of restId=" + restid;
		try {
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setFoodtype(foodtype);
			menuInfo.setHot(hot);
			menuInfo.setMenuname(menuname);
			menuInfo.setMenuprice(menuprice);
			menuInfo.setRecommand(recommand);
			menuInfo.setRestid(restid);
			menuService.addMenu(menuInfo);
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping("/updateMenu")
	@ResponseBody
	public JsonResult<String> updateMenu(@RequestParam int menuid, @RequestParam int restid, @RequestParam String menuname, 
			@RequestParam Double menuprice, @RequestParam Integer foodtype, @RequestParam Integer recommand, @RequestParam Integer hot){
		Boolean flag = true;
		String message = "Update menu of restId=" + restid;
		try {
			MenuInfo menuInfo = menuService.getMenuInfoDetail(menuid);
			menuInfo.setFoodtype(foodtype);
			menuInfo.setHot(hot);
			menuInfo.setMenuname(menuname);
			menuInfo.setMenuprice(menuprice);
			menuInfo.setRecommand(recommand);
			menuInfo.setRestid(restid);
			menuService.updateMenu(menuInfo);
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public JsonResult<String> deleteMenu(@RequestParam int menuid){
		Boolean flag = true;
		String message = "deleteMenu menu of menuId=" + menuid;
		try {
			menuService.deleteMenu(menuid);
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return new JsonResult<String>(flag, message, null);
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
	
	@RequestMapping("/addFoodType")
	@ResponseBody
	public JsonResult<String> addFoodType(@RequestParam String foodTypeName){
		Boolean flag = true;
		String message = "AddFoodType " + foodTypeName;
		try {
			FoodType foodType = new FoodType();
			foodType.setFoodtypename(foodTypeName);
			menuService.addFoodType(foodType);
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			flag = false;
			message = message + "error! Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}

	@RequestMapping("/deleteFoodType")
	@ResponseBody
	public JsonResult<String> deleteFoodType(@RequestParam int foodTypeId){
		Boolean flag = true;
		String message = "DeleteFoodType " + foodTypeId;
		try {
			menuService.deleteFoodType(foodTypeId);
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			flag = false;
			message = message + "error! Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}
}
