package com.threeone.mealplanner.mapper;

import java.util.List;

import com.threeone.mealplanner.model.entity.FoodType;

public interface FoodTypeMapper {
    int deleteByPrimaryKey(Integer foodtypeid);

    int insert(FoodType record);

    int insertSelective(FoodType record);

    FoodType selectByPrimaryKey(Integer foodtypeid);

    int updateByPrimaryKeySelective(FoodType record);

    int updateByPrimaryKey(FoodType record);
    
    List<FoodType> getAllFoodTypes();
    
    
}