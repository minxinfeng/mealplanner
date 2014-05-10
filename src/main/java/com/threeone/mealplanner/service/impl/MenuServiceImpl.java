package com.threeone.mealplanner.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.FoodTypeMapper;
import com.threeone.mealplanner.mapper.MenuInfoMapper;
import com.threeone.mealplanner.mapper.RestUserMapper;
import com.threeone.mealplanner.model.entity.FoodType;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.service.MenuService;

public class MenuServiceImpl implements MenuService {

	private FoodTypeMapper foodTypeMapper;
	private MenuInfoMapper menuInfoMapper;

	private static final Log LOG = LogFactory.getLog(MenuServiceImpl.class);
	
	
	public List<MenuInfo> getMenuInfoByRestId(int restId) throws InternalException {
		try {
			return menuInfoMapper.getMenuByRestId(restId);
		} catch (Exception e) {
			String message = "fail to get the menu info of the restaurant where restId=" + restId + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public List<MenuInfo> getMenuInfoByType(int typeId) throws InternalException {
		try {
			return menuInfoMapper.getMenuInfoByType(typeId);
		} catch (Exception e) {
			String message = "fail to get the menu info of foodtype=" + typeId + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	
	
	public MenuInfo getMenuInfoDetail(int menuId) throws InternalException {
		try {
			return menuInfoMapper.selectByPrimaryKey(menuId);
		} catch (Exception e) {
			String message = "fail to get the menu detail info of menuId=" + menuId + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int addMenu(MenuInfo menuInfo) throws InternalException {
		try {
			return menuInfoMapper.insertSelective(menuInfo);
		} catch (Exception e) {
			String message = "fail to add the menu " + menuInfo.getMenuname() + " of restId=" + menuInfo.getRestid() + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int updateMenu(MenuInfo menuInfo) throws InternalException {
		try {
			return menuInfoMapper.updateByPrimaryKeySelective(menuInfo);
		} catch (Exception e) {
			String message = "fail to update the menu " + menuInfo.getMenuname() + " of restId=" + menuInfo.getRestid() + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int deleteMenu(int menuId) throws InternalException {
		try {
			return menuInfoMapper.deleteByPrimaryKey(menuId);
		} catch (Exception e) {
			String message = "fail to delete the menuId " + menuId + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public List<FoodType> getAllFoodTypes() throws InternalException {
		try {
			return foodTypeMapper.getAllFoodTypes();
		} catch (Exception e) {
			String message = "Error to get all food type, Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int addFoodType(FoodType foodType) throws InternalException {
		try {
			return foodTypeMapper.insertSelective(foodType);
		} catch (Exception e) {
			String message = "Error to add foodType:" + foodType.getFoodtypename() + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int deleteFoodType(int foodtypeid) throws InternalException {
		try {
			return foodTypeMapper.deleteByPrimaryKey(foodtypeid);
		} catch (Exception e) {
			String message = "Error to add delete:" + foodtypeid + ", Reason: " + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}	


	public void setFoodTypeMapper(FoodTypeMapper foodTypeMapper) {
		this.foodTypeMapper = foodTypeMapper;
	}

	public void setMenuInfoMapper(MenuInfoMapper menuInfoMapper) {
		this.menuInfoMapper = menuInfoMapper;
	}

}
