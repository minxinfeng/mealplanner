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
import com.threeone.mealplanner.model.RestInfoForMap;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.RestCity;
import com.threeone.mealplanner.model.entity.RestUser;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {
	
	private static final Log LOG = LogFactory.getLog(RestaurantServiceImpl.class);
	
	private RestaurantInfoMapper restaurantInfoMapper;
	private MenuInfoMapper menuInfoMapper;
	private RestUserMapper restUserMapper;
	private RestCityMapper restCityMapper;
	
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
			int limit) throws InternalException {
		List<RestaurantWithMenu> restaurantWithMenus = new ArrayList<RestaurantWithMenu>();
		try {
			List<RestaurantInfo> restaurantInfos = restaurantInfoMapper.getSeveralRestaurantInfos(start, limit);
			for (RestaurantInfo restaurantInfo : restaurantInfos) {
				RestaurantWithMenu restaurantWithMenu = new RestaurantWithMenu();
				List<MenuInfo> menuInfos = menuInfoMapper.getMenuByRestId(restaurantInfo.getRestid());
				restaurantWithMenu.setRestaurantInfo(restaurantInfo);
				restaurantWithMenu.setMenuInfos(menuInfos);
				restaurantWithMenus.add(restaurantWithMenu);
			}
			LOG.info("Get restaurants with menu info from " + start + "  limit to " + limit + " success!");
		} catch (Exception e) {
			String message = "Get restaurants with menu info from " + start + "  limit to " + limit + "failed, Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		return restaurantWithMenus;
	}
	
	public List<RestaurantInfo> getSeveralRest(int start, int limit) throws InternalException{
		List<RestaurantInfo> restaurantInfos = new ArrayList<RestaurantInfo>();
		try {
			restaurantInfos = restaurantInfoMapper.getSeveralRestaurantInfos(start, limit);
			LOG.info("Get restaurants info from " + start + "  limit to " + limit + " success!");
		} catch (Exception e) {
			String message = "Get restaurants from " + start + "  limit to " + limit + "failed, Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		return restaurantInfos;
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
			RestaurantInfo restaurantInfo = restaurantInfoMapper.getRestInfoByExactName(restName);
			if(restaurantInfo == null){
				throw new InternalException("Don't have the restaurant " + restName);
			}
			return restaurantInfo;
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
			return restaurantInfoMapper.insertSelective(restaurantInfo);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}		
	}


	public int mapRestaurantUser(RestUser restUser) throws InternalException {
		try {
			return restUserMapper.insertSelective(restUser);
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

	public RestaurantInfo getRestNameByUser(UserInfo userInfo)
			throws InternalException {
		try {
			RestUser restUser = restUserMapper.getRestUserByUserId(userInfo.getUserid());		
			return restaurantInfoMapper.selectByPrimaryKey(restUser.getRestid());
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}
	

	public int getRestIdByUserId(int userId) throws InternalException {
		try {
			return restUserMapper.getRestUserByUserId(userId).getRestid();
		} catch (Exception e) {
			String message = "Error to get restId by userId : " + userId + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	
	public List<RestInfoForMap> getRestInfoForMaps(String restNames) throws InternalException{
		try {
			List<RestInfoForMap> restInfoForMaps = new ArrayList<RestInfoForMap>();
			String names[] = restNames.split(",");
			for (String name : names) {
				List<RestInfoForMap> restForMaps = restaurantInfoMapper.getRestInfoForMaps(name);
				if (restForMaps != null) {
					restInfoForMaps.addAll(restForMaps);
				}
			}
			LOG.info("Success to get the restInfo for names=" + restNames);
			return restInfoForMaps;
		} catch (Exception e) {
			String message = "Error to get restInfos by restNames= " + restNames + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	
	public void setRestCityMapper(RestCityMapper restCityMapper) {
		this.restCityMapper = restCityMapper;
	}

	public void setRestUserMapper(RestUserMapper restUserMapper) {
		this.restUserMapper = restUserMapper;
	}

	
	public void setRestaurantInfoMapper(RestaurantInfoMapper restaurantInfoMapper) {
		this.restaurantInfoMapper = restaurantInfoMapper;
	}

	public void setMenuInfoMapper(MenuInfoMapper menuInfoMapper) {
		this.menuInfoMapper = menuInfoMapper;
	}

}
