package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.OrderInfo;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}