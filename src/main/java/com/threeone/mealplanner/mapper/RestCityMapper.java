package com.threeone.mealplanner.mapper;

import java.util.List;

import com.threeone.mealplanner.model.entity.RestCity;

public interface RestCityMapper {
    int deleteByPrimaryKey(Integer cityid);

    int insert(RestCity record);

    int insertSelective(RestCity record);

    RestCity selectByPrimaryKey(Integer cityid);

    int updateByPrimaryKeySelective(RestCity record);

    int updateByPrimaryKey(RestCity record);

	List<RestCity> getAllCity();
}