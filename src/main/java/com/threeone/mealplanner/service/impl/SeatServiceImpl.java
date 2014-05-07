package com.threeone.mealplanner.service.impl;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.SeatInfoMapper;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.service.SeatService;

public class SeatServiceImpl implements SeatService {
	
	private SeatInfoMapper seatInfoMapper;
	
	@Override
	public List<SeatInfo> getAvailableSeats(int restId, String dateDay,
			int dateClock, int peopleNum) throws InternalException {
		List<SeatInfo> availableSeats = seatInfoMapper.getAvailableSeatInfos(restId, dateDay, dateClock, peopleNum);
		return availableSeats;
	}

}
