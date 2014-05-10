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
 * ������Ϣ����
 * @author fengxiangmin wangyongbo
 *
 */
public interface RestaurantService {
	
	/**
	 * ��ȡ���еĲ�����Ϣ�Լ���Ӧ�Ĳ˵���Ϣ
	 * @return
	 * @throws InternalException 
	 */
	List<RestaurantWithMenu> getAllRestaurantWithMenus() throws InternalException;
	
	List<RestaurantWithMenu> getSeveralRestaurantWithMenus(int start, int end) throws InternalException;
	
	/**
	 * ��ȡ����������Ϣ
	 * @param restId
	 * @return
	 * @throws InternalException
	 */
	RestaurantInfo getRestaurantInfo(int restId) throws InternalException;
	
	RestaurantWithMenu getRestaurantInfoWithMenu(int restId) throws InternalException;
	
	RestaurantInfo getRestInfoByExactName(@Param("restName") String restName) throws InternalException;
    
    List<RestaurantInfo> getRestsByName(@Param("restName") String restName) throws InternalException;
    
    /**
	 * ע�������Ϣ
	 * @return
	 * @throws InternalException 
	 */
	int registRestaurant(RestaurantInfo restaurantInfo) throws InternalException;
	
	/**
	 * ��Ӳ����û���Ӧ��ϵ
	 * @return
	 * @throws InternalException 
	 */
	int mapRestaurantUser(RestUser restUser) throws InternalException;
	
	/**
	 * ����userId���restaurantInfo
	 * @param userInfo
	 * @return
	 * @throws InternalException 
	 */
	RestaurantInfo getRestNameByUser(UserInfo userInfo) throws InternalException;
	
	/**
	 * ������г���
	 * @return
	 * @throws InternalException 
	 */
	List<RestCity> getAllCity() throws InternalException;
	

	/**
	 * ͨ���û�Id��ȡ����Id
	 * @param userId
	 * @return restId
	 * @throws InternalException 
	 */
	int getRestIdByUserId(int userId) throws InternalException;
}
