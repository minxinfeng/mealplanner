package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.MealFriend;

public interface MealFriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MealFriend record);

    int insertSelective(MealFriend record);

    MealFriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MealFriend record);

    int updateByPrimaryKey(MealFriend record);
}