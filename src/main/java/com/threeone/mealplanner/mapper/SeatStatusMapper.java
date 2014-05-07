package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.model.entity.SeatStatus;

public interface SeatStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeatStatus record);

    int insertSelective(SeatStatus record);

    SeatStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeatStatus record);

    int updateByPrimaryKey(SeatStatus record);    

    int insertSeatStatus(@Param("seatId") int seatId, @Param("restId") int restId, @Param("dateDay") String dateDay,
    		@Param("dateClock") int dateClock, @Param("state") int state);
    
    List<SeatStatus> getNotAvailableOfSeatWholeDay(@Param("seatId") int seatId, @Param("restId") int restId, 
    		@Param("dateDay") String dateDay);
}