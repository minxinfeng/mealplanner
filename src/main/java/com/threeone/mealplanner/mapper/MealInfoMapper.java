package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.MealInfo;

public interface MealInfoMapper {
    int deleteByPrimaryKey(Integer mealid);

    int insert(MealInfo record);

    int insertSelective(MealInfo record);

    MealInfo selectByPrimaryKey(Integer mealid);

    int updateByPrimaryKeySelective(MealInfo record);

    int updateByPrimaryKey(MealInfo record);
}