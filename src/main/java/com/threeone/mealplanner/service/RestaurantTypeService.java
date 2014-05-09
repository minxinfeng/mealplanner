package com.threeone.mealplanner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.RestaurantWithMenu;
import com.threeone.mealplanner.model.entity.RestType;
import com.threeone.mealplanner.model.entity.RestaurantInfo;

public interface RestaurantTypeService {
	List<RestType> getAllType() throws InternalException;
}
