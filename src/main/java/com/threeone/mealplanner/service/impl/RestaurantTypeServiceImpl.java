package com.threeone.mealplanner.service.impl;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.RestTypeMapper;
import com.threeone.mealplanner.model.entity.RestType;
import com.threeone.mealplanner.service.RestaurantTypeService;

public class RestaurantTypeServiceImpl implements RestaurantTypeService {

	private RestTypeMapper restTypeMapper;
	
	public void setRestTypeMapper(RestTypeMapper restTypeMapper) {
		this.restTypeMapper = restTypeMapper;
	}

	public List<RestType> getAllType() throws InternalException {		
		try {
			return restTypeMapper.getAllType();
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}

}
