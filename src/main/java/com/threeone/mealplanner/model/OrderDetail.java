package com.threeone.mealplanner.model;

import java.util.List;

import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.OrderInfo;

public class OrderDetail {
	
	private OrderInfo orderInfo;
	private String restName;
	private List<MenuInfo> menuInfos;
	
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public List<MenuInfo> getMenuInfos() {
		return menuInfos;
	}
	public void setMenuInfos(List<MenuInfo> menuInfos) {
		this.menuInfos = menuInfos;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	
}
