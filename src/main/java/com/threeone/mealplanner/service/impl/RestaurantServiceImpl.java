package com.threeone.mealplanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.MenuInfoMapper;
import com.threeone.mealplanner.mapper.RestCityMapper;
import com.threeone.mealplanner.mapper.RestUserMapper;
import com.threeone.mealplanner.mapper.RestaurantInfoMapper;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.RestCity;
import com.threeone.mealplanner.model.entity.RestUser;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {
	
	private static final Log LOG = LogFactory.getLog(RestaurantServiceImpl.class);
	
	private RestaurantInfoMapper restaurantInfoMapper;
	private MenuInfoMapper menuInfoMapper;
	private RestUserMapper restUserMapper;
	private RestCityMapper restCityMapper;
	
	
	public void setRestCityMapper(RestCityMapper restCityMapper) {
		this.restCityMapper = restCityMapper;
	}

	public void setRestUserMapper(RestUserMapper restUserMapper) {
		this.restUserMapper = restUserMapper;
	}

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


	
	public RestaurantInfo getRestaurantInfo(int restId)
			throws InternalException {
		try {
			return restaurantInfoMapper.selectByPrimaryKey(restId);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}


	
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


	
	public RestaurantInfo getRestInfoByExactName(String restName) throws InternalException {
		try {
			return restaurantInfoMapper.getRestInfoByExactName(restName);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}


	
	public List<RestaurantInfo> getRestsByName(String restName) throws InternalException{
		try {
			return restaurantInfoMapper.getRestsByName(restName);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}



	public int registRestaurant(RestaurantInfo restaurantInfo)
			throws InternalException {
		try {
			return restaurantInfoMapper.insert(restaurantInfo);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}		
	}


	public int mapRestaurantUser(RestUser restUser) throws InternalException {
		try {
			return restUserMapper.insert(restUser);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}

	public List<RestCity> getAllCity() throws InternalException {
		try {
			return restCityMapper.getAllCity();
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}
}
