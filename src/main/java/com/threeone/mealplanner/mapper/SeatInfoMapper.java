package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.SeatInfo;

public interface SeatInfoMapper {
    int deleteByPrimaryKey(Integer seatid);

    int insert(SeatInfo record);

    int insertSelective(SeatInfo record);

    SeatInfo selectByPrimaryKey(Integer seatid);

    int updateByPrimaryKeySelective(SeatInfo record);

    int updateByPrimaryKey(SeatInfo record);
}