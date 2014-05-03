package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.SeatStatus;

public interface SeatStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeatStatus record);

    int insertSelective(SeatStatus record);

    SeatStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeatStatus record);

    int updateByPrimaryKey(SeatStatus record);
}