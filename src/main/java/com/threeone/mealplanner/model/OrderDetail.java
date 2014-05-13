package com.threeone.mealplanner.model;

import java.util.List;

import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.OrderInfo;
import com.threeone.mealplanner.model.entity.RestaurantInfo;

public class OrderDetail {
	
	private OrderInfo orderInfo;
	private MealWithDetail mealWithDetail;
	private List<MenuInfo> menuInfos;
	private RestaurantInfo restaurantInfo;
	
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public MealWithDetail getMealWithDetail() {
		return mealWithDetail;
	}
	public void setMealWithDetail(MealWithDetail mealWithDetail) {
		this.mealWithDetail = mealWithDetail;
	}
	public List<MenuInfo> getMenuInfos() {
		return menuInfos;
	}
	public void setMenuInfos(List<MenuInfo> menuInfos) {
		this.menuInfos = menuInfos;
	}
	public RestaurantInfo getRestaurantInfo() {
		return restaurantInfo;
	}
	public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
		this.restaurantInfo = restaurantInfo;
	}
	
}
