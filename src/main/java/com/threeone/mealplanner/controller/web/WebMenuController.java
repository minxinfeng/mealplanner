package com.threeone.mealplanner.controller.web;

import java.util.List;

import org.apache.commons.collections.functors.IfClosure;
import org.apache.log4j.lf5.viewer.LogFactor5Dialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.log.Log;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.entity.FoodType;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.service.MenuService;

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
	
	@RequestMapping("/getMenuByRestId")
	public String getMenuInfoByRestId(@RequestParam int restId, Model model){
		try {
			List<MenuInfo> menuInfos = menuService.getMenuInfoByRestId(restId);
			model.addAttribute("menuInfos", menuInfos);
			return "menu/menu.ftl";
		} catch (Exception e) {
			return "menu/menu.ftl";
		}
	}
	
	@RequestMapping("/getMenuByFoodType")
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
	public String addMenu(@RequestParam int restId, @RequestParam String foodName, 
			@RequestParam Double foodPrice, @RequestParam Integer foodType, @RequestParam Boolean recommand){
		Boolean flag = true;
		String message = "Add menu of restId=" + restId;
		try {
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
			return "menu/menu.ftl";
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return "menu/menu.ftl";
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
	
	@RequestMapping("/updateMenuPart")
	@ResponseBody
	public String updateMenuPart(@RequestParam int menuId, @RequestParam int restId, @RequestParam String foodName, 
			@RequestParam Double foodPrice, @RequestParam Integer foodType, @RequestParam Boolean recommand){
		Boolean flag = true;
		String message = "Update menu of restId=" + restId;
		try {
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
			return "/web/menu/getMenuByRestId?restId=" + restId;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
			return "web/menu/error";
		}
	}
	
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public String deleteMenu(@RequestParam int menuid){
		Boolean flag = true;
		String message = "deleteMenu menu of menuId=" + menuid;
		try {
			menuService.deleteMenu(menuid);
			message += " success!";
			return message;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			flag = false;
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
