package com.threeone.mealplanner.service.impl;

import java.util.HashMap;
import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.SeatInfoMapper;
import com.threeone.mealplanner.mapper.SeatStatusMapper;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.service.SeatService;

public class SeatServiceImpl implements SeatService {
	private static int RESERVED = 1;
	private static int OCCUPIED = 2;
	
	private SeatInfoMapper seatInfoMapper;
	private SeatStatusMapper seatStatusMapper;
	
	public void setSeatInfoMapper(SeatInfoMapper seatInfoMapper) {
		this.seatInfoMapper = seatInfoMapper;
	}

	public void setSeatStatusMapper(SeatStatusMapper seatStatusMapper) {
		this.seatStatusMapper = seatStatusMapper;
	}	
	
	@Override
	public List<SeatInfo> getAvailableSeats(int restId, String dateDay,
			int dateClock, int peopleNum) throws InternalException {
		List<SeatInfo> availableSeats = seatInfoMapper.getAvailableSeatInfos(restId, dateDay, dateClock, peopleNum);
		return availableSeats;
	}

	@Override
	public SeatInfo reserveSeat(int restId, String dateDay, int dateClock,
			int peopleNum) throws InternalException {
		List<SeatInfo> availableSeats = seatInfoMapper.getAvailableSeatInfos(restId, dateDay, dateClock, peopleNum);
		if(!availableSeats.isEmpty()){
			SeatInfo avSeat = availableSeats.get(0);
			seatStatusMapper.insertSeatStatus(avSeat.getSeatid(), restId, dateDay, dateClock, RESERVED);
			seatStatusMapper.insertSeatStatus(avSeat.getSeatid(), restId, dateDay, dateClock + 1, RESERVED);// default use time is 2 hours
			return avSeat;
		}
		return null;
	}

	@Override
	public void occupySeat(int seatId, int restId, String dateDay,
			int dateClock, int peopleNum) throws InternalException {
		seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock, OCCUPIED);
		seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock + 1, OCCUPIED);
	}

	@Override
	public HashMap<Integer, Integer> getStateOfSeatWholeDay(int seatId,
			int restId, String dateDay) throws InternalException {
		HashMap<Integer, Integer> stateHashMap = new HashMap<Integer, Integer>();
		// TODO Auto-generated method stub
		return stateHashMap;
	}
	
}
