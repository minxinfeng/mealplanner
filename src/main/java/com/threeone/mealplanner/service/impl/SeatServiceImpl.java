package com.threeone.mealplanner.service.impl;

import java.util.HashMap;
import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.SeatInfoMapper;
import com.threeone.mealplanner.mapper.SeatStatusMapper;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.model.entity.SeatStatus;
import com.threeone.mealplanner.service.SeatService;

public class SeatServiceImpl implements SeatService {
	private static int RESERVED = 1;
	private static int OCCUPIED = 2;
	private static Integer AVAILABLE = 0;
	
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
		int[] clockState = new int[13];
		List<SeatStatus> notAvailableSeats = seatStatusMapper.getNotAvailableOfSeatWholeDay(seatId, restId, dateDay);
		for(SeatStatus seatStatus : notAvailableSeats){
			stateHashMap.put(seatStatus.getDateclock(), seatStatus.getState());
			clockState[seatStatus.getDateclock()] = 1;
		}
		for(int i = 0; i < clockState.length; i++){
			if(clockState[i] != 1){
				stateHashMap.put(i + 10, AVAILABLE);
			}
		}
		return stateHashMap;
	}

	@Override
	public void reserveSeatById(int seatId, int restId, String dateDay,
			int dateClock) throws InternalException {
		seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock, RESERVED);
		seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock + 1, RESERVED);
	}

	@Override
	public void freeSeatById(int seatId, int restId, String dateDay,
			int dateClock) throws InternalException {
		seatStatusMapper.deleteBySeatIdClock(seatId, restId, dateDay, dateClock);
		seatStatusMapper.deleteBySeatIdClock(seatId, restId, dateDay, dateClock + 1);
	}
	
}
