package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.entity.FoodType;
import com.threeone.mealplanner.model.entity.MenuInfo;

public interface MenuService {
	
	List<MenuInfo> getMenuInfoByRestId(int restId) throws InternalException;
	
	List<MenuInfo> getMenuInfoByType(int typeId) throws InternalException;
	
	MenuInfo getMenuInfoDetail(int menuId) throws InternalException;
	
	int addMenu(MenuInfo menuInfo) throws InternalException;
	
	int updateMenu(MenuInfo menuInfo) throws InternalException;
	
	int deleteMenu(int menuId) throws InternalException;
	
	List<FoodType> getAllFoodTypes() throws InternalException;
	
	int addFoodType(FoodType foodType) throws InternalException;
	
	int deleteFoodType(int foodtypeid) throws InternalException;
}
