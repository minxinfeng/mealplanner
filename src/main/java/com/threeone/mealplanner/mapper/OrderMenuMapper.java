package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.OrderMenu;

public interface OrderMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderMenu record);

    int insertSelective(OrderMenu record);

    OrderMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderMenu record);

    int updateByPrimaryKey(OrderMenu record);
}