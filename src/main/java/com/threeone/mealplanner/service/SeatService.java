package com.threeone.mealplanner.service;

import java.util.HashMap;
import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.entity.SeatInfo;

/**
 * 座位状态信息管理
 * @author wangyongbo
 *
 */
public interface SeatService {

	/**
	 * 获取指定时间段所有人数满足条件的可用座位
	 * @return
	 * @throws InternalException 
	 */
	List<SeatInfo> getAvailableSeats(int restId, String dateDay, int dateClock, int peopleNum) throws InternalException;
	
	/**
	 * 预定指定时间段所有人数满足条件的可用座位
	 * @return
	 * @throws InternalException 
	 */
	SeatInfo reserveSeat(int restId, String dateDay, int dateClock, int peopleNum) throws InternalException;
	
	/**
	 * 占用指定时间段所有人数满足条件的可用座位
	 * @return
	 * @throws InternalException 
	 */
	void occupySeat(int seatId, int restId, String dateDay, int dateClock, int peopleNum) throws InternalException;
	
	/**
	 * 得到指定餐厅的某一座位的一天的所有时间的所有状态
	 * @return 
	 * @return
	 * @throws InternalException 
	 */
	HashMap<Integer, Integer> getStateOfSeatWholeDay(int seatId, int restId, String dateDay) throws InternalException;
}
