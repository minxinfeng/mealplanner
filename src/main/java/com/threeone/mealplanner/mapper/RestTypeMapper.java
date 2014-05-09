package com.threeone.mealplanner.mapper;

import java.util.List;

import com.threeone.mealplanner.model.entity.RestType;

public interface RestTypeMapper {
    int deleteByPrimaryKey(Integer resttypeid);

    int insert(RestType record);

    int insertSelective(RestType record);

    RestType selectByPrimaryKey(Integer resttypeid);

    int updateByPrimaryKeySelective(RestType record);

    int updateByPrimaryKey(RestType record);

	List<RestType> getAllType();
}