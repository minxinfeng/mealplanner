package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.RestUser;

public interface RestUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RestUser record);

    int insertSelective(RestUser record);

    RestUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RestUser record);

    int updateByPrimaryKey(RestUser record);
}