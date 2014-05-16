package com.threeone.mealplanner.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.entity.FoodType;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.RestType;
import com.threeone.mealplanner.service.MenuService;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.RestaurantTypeService;

/**
 * Need to update for the web side
 * @author kyle
 *
 */
@Controller
@RequestMapping("/web/menu")
public class WebMenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantTypeService restaurantTypeService;
	
	@RequestMapping("/menu")
	public String getMenuInfo(){
		return "menu/menu.ftl";
	}
	
	@RequestMapping("/getMenuByUserId")
	public String getMenuInfoByRestId(@RequestParam int userId, Model model){
		List<FoodType> foodTypes;
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			List<MenuInfo> menuInfos = menuService.getMenuInfoByRestId(restId);
			foodTypes = menuService.getAllFoodTypes();			
			model.addAttribute("foodTypes", foodTypes);
			model.addAttribute("menuInfos", menuInfos);
			return "menu/menu.ftl";
		} catch (Exception e) {
			return "menu/menu.ftl";
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
	
	@RequestMapping(value="/addMenu", method = RequestMethod.POST)
	public String addMenu(@RequestParam int userId, @RequestParam String foodName, 
			@RequestParam Double foodPrice, @RequestParam Integer foodType, @RequestParam Boolean recommand){
		System.out.println("add menu");
		String message = "Add menu of userId=" + userId;
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setFoodtype(foodType);
			menuInfo.setMenuname(foodName);
			menuInfo.setMenuprice(foodPrice);
			if(recommand){
				menuInfo.setRecommand(1);
			}else{
				menuInfo.setRecommand(0);
			}			
			menuInfo.setRestid(restId);
			menuService.addMenu(menuInfo);
			message += " success!";
			return "redirect:getMenuByUserId?userId=" + userId;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return "redirect:getMenuByUserId?userId=" + userId;
		}
	}
	
	@RequestMapping("/updateMenu")
	@ResponseBody
	public JsonResult<String> updateMenu(@RequestParam int menuid, @RequestParam int userId, @RequestParam String menuname, 
			@RequestParam Double menuprice, @RequestParam Integer foodtype, @RequestParam Integer recommand, @RequestParam Integer hot){
		Boolean flag = true;
		String message = "Update menu of restId=" + userId;
		try {
			int restid = restaurantService.getRestIdByUserId(userId);
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
	
	@RequestMapping(value = "/updateMenuPart", method = RequestMethod.GET)
	public String updateMenuPart(@RequestParam int menuId, @RequestParam int userId, @RequestParam String foodName, 
			@RequestParam Double foodPrice, @RequestParam Integer foodType, @RequestParam Boolean recommand){
		String message = "Update menu of restId=" + userId;
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			MenuInfo menuInfo = menuService.getMenuInfoDetail(menuId);
			menuInfo.setFoodtype(foodType);
			menuInfo.setMenuname(foodName);
			menuInfo.setMenuprice(foodPrice);
			if(recommand){
				menuInfo.setRecommand(1);
			}else{
				menuInfo.setRecommand(0);
			}
			menuInfo.setRestid(restId);
			menuService.updateMenu(menuInfo);
			message += " success!";
			return "redirect:getMenuByUserId?userId=" + userId;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return "redirect:getMenuByUserId?userId=" + userId;
		}
	}
	
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public String deleteMenu(@RequestParam int menuid){
		String message = "deleteMenu menu of menuId=" + menuid;
		try {
			menuService.deleteMenu(menuid);
			message += " success!";
			return message;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return message;
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
