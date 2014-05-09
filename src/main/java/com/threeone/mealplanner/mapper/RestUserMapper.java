package com.threeone.mealplanner.mapper;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.RestUser;

public interface RestUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RestUser record);

    int insertSelective(RestUser record);

    RestUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RestUser record);

    int updateByPrimaryKey(RestUser record);

	RestUser getRestUserByUserId(@Param("userId") Integer userId);
}