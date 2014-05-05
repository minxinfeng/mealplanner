package com.threeone.mealplanner.model;

import java.util.List;

import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.RestaurantInfo;

public class RestaurantWithMenu {
	
	private RestaurantInfo restaurantInfo;
	private List<MenuInfo> menuInfos;
	
	public RestaurantInfo getRestaurantInfo() {
		return restaurantInfo;
	}
	public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
		this.restaurantInfo = restaurantInfo;
	}
	public List<MenuInfo> getMenuInfos() {
		return menuInfos;
	}
	public void setMenuInfos(List<MenuInfo> menuInfos) {
		this.menuInfos = menuInfos;
	}
	
}
