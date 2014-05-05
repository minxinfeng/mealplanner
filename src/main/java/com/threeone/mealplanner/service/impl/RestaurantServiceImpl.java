package com.threeone.mealplanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.MenuInfoMapper;
import com.threeone.mealplanner.mapper.RestaurantInfoMapper;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {
	
	private static final Log LOG = LogFactory.getLog(RestaurantServiceImpl.class);
	
	private RestaurantInfoMapper restaurantInfoMapper;
	private MenuInfoMapper menuInfoMapper;
	
	@Override
	public List<RestaurantWithMenu> getAllRestaurantWithMenus() throws InternalException{
		List<RestaurantWithMenu> restaurantWithMenus = new ArrayList<RestaurantWithMenu>();
		List<RestaurantInfo> restaurantInfos = restaurantInfoMapper.getAllRestaurantInfos();
		for (RestaurantInfo restaurantInfo : restaurantInfos) {
			RestaurantWithMenu restaurantWithMenu = new RestaurantWithMenu();
			List<MenuInfo> menuInfos = menuInfoMapper.getMenuByRestId(restaurantInfo.getRestid());
			restaurantWithMenu.setRestaurantInfo(restaurantInfo);
			restaurantWithMenu.setMenuInfos(menuInfos);
			restaurantWithMenus.add(restaurantWithMenu);
		}
		return restaurantWithMenus;
	}

	
	@Override
	public List<RestaurantWithMenu> getSeveralRestaurantWithMenus(int start,
			int end) throws InternalException {
		List<RestaurantWithMenu> restaurantWithMenus = new ArrayList<RestaurantWithMenu>();
		List<RestaurantInfo> restaurantInfos = restaurantInfoMapper.getSeveralRestaurantInfos(start, end);
		for (RestaurantInfo restaurantInfo : restaurantInfos) {
			RestaurantWithMenu restaurantWithMenu = new RestaurantWithMenu();
			List<MenuInfo> menuInfos = menuInfoMapper.getMenuByRestId(restaurantInfo.getRestid());
			restaurantWithMenu.setRestaurantInfo(restaurantInfo);
			restaurantWithMenu.setMenuInfos(menuInfos);
			restaurantWithMenus.add(restaurantWithMenu);
		}
		return restaurantWithMenus;
	}
	
	public void setRestaurantInfoMapper(RestaurantInfoMapper restaurantInfoMapper) {
		this.restaurantInfoMapper = restaurantInfoMapper;
	}

	public void setMenuInfoMapper(MenuInfoMapper menuInfoMapper) {
		this.menuInfoMapper = menuInfoMapper;
	}

}
