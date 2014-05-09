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


	@Override
	public RestaurantInfo getRestaurantInfo(int restId)
			throws InternalException {
		try {
			return restaurantInfoMapper.selectByPrimaryKey(restId);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}


	@Override
	public RestaurantWithMenu getRestaurantInfoWithMenu(int restId)
			throws InternalException {
		try {
			RestaurantInfo restaurantInfo = restaurantInfoMapper.selectByPrimaryKey(restId);
			RestaurantWithMenu restaurantWithMenu = new RestaurantWithMenu();
			List<MenuInfo> menuInfos = menuInfoMapper.getMenuByRestId(restaurantInfo.getRestid());
			restaurantWithMenu.setRestaurantInfo(restaurantInfo);
			restaurantWithMenu.setMenuInfos(menuInfos);
			return restaurantWithMenu;
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}


	@Override
	public RestaurantInfo getRestInfoByExactName(String restName) throws InternalException {
		try {
			return restaurantInfoMapper.getRestInfoByExactName(restName);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}


	@Override
	public List<RestaurantInfo> getRestsByName(String restName) throws InternalException{
		try {
			return restaurantInfoMapper.getRestsByName(restName);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}

}
