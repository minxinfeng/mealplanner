package com.threeone.mealplanner.mapper;

import java.util.List;

import com.threeone.mealplanner.model.entity.RestaurantInfo;

public interface RestaurantInfoMapper {
    int deleteByPrimaryKey(Integer restid);

    int insert(RestaurantInfo record);

    int insertSelective(RestaurantInfo record);

    RestaurantInfo selectByPrimaryKey(Integer restid);

    int updateByPrimaryKeySelective(RestaurantInfo record);

    int updateByPrimaryKey(RestaurantInfo record);
    
    List<RestaurantInfo> getAllRestaurantInfos();
}