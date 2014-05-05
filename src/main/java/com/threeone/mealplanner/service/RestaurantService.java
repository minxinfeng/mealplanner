package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.RestaurantWithMenu;


/**
 * 餐厅信息管理
 * @author fengxiangmin
 *
 */
public interface RestaurantService {
	
	/**
	 * 获取所有的餐厅信息以及对应的菜单信息
	 * @return
	 * @throws InternalException 
	 */
	List<RestaurantWithMenu> getAllRestaurantWithMenus() throws InternalException;
	
	List<RestaurantWithMenu> getSeveralRestaurantWithMenus(int start, int end) throws InternalException;
}
