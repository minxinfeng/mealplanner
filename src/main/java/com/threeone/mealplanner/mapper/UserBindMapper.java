package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.UserBind;

public interface UserBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBind record);

    int insertSelective(UserBind record);

    UserBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBind record);

    int updateByPrimaryKey(UserBind record);
}