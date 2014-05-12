package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.UserBind;

public interface UserBindMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserBind record);

    int insertSelective(UserBind record);

    UserBind selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserBind record);

    int updateByPrimaryKey(UserBind record);
}