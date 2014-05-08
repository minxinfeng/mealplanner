package com.threeone.mealplanner.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.SeatInfo;

public interface SeatInfoMapper {
    int deleteByPrimaryKey(Integer seatid);

    int insert(SeatInfo record);

    int insertSelective(SeatInfo record);

    SeatInfo selectByPrimaryKey(Integer seatid);

    int updateByPrimaryKeySelective(SeatInfo record);

    int updateByPrimaryKey(SeatInfo record);
    
    List<SeatInfo> getAvailableSeatInfos(@Param("restId") int restId, @Param("dateDay") String dateDay,
    		@Param("dateClock") int dateClock, @Param("peopleNum") int peopleNum);

	List<SeatInfo> getSeatsByRestId(@Param("restId") int restId);
}