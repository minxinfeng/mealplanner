package com.threeone.mealplanner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.RestCity;
import com.threeone.mealplanner.model.entity.RestUser;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.model.entity.UserInfo;


/**
 * 餐厅信息管理
 * @author fengxiangmin wangyongbo
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
	
	/**
	 * 获取餐厅基本信息
	 * @param restId
	 * @return
	 * @throws InternalException
	 */
	RestaurantInfo getRestaurantInfo(int restId) throws InternalException;
	
	RestaurantWithMenu getRestaurantInfoWithMenu(int restId) throws InternalException;
	
	RestaurantInfo getRestInfoByExactName(@Param("restName") String restName) throws InternalException;
    
    List<RestaurantInfo> getRestsByName(@Param("restName") String restName) throws InternalException;
    
    /**
	 * 注册餐厅信息
	 * @return
	 * @throws InternalException 
	 */
	int registRestaurant(RestaurantInfo restaurantInfo) throws InternalException;
	
	/**
	 * 添加餐厅用户对应关系
	 * @return
	 * @throws InternalException 
	 */
	int mapRestaurantUser(RestUser restUser) throws InternalException;
	
	/**
	 * 根据userId获得restaurantInfo
	 * @param userInfo
	 * @return
	 * @throws InternalException 
	 */
	RestaurantInfo getRestNameByUser(UserInfo userInfo) throws InternalException;
	
	/**
	 * 获得所有城市
	 * @return
	 * @throws InternalException 
	 */
	List<RestCity> getAllCity() throws InternalException;
	

	/**
	 * 通过用户Id获取餐厅Id
	 * @param userId
	 * @return restId
	 * @throws InternalException 
	 */
	int getRestIdByUserId(int userId) throws InternalException;
}
