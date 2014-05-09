package com.threeone.mealplanner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.RestaurantInfo;


/**
 * ������Ϣ����
 * @author fengxiangmin
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
}
