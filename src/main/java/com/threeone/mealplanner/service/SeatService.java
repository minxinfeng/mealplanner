package com.threeone.mealplanner.service;

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
}
