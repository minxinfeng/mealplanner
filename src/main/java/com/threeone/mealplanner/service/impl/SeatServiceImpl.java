package com.threeone.mealplanner.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.SeatInfoMapper;
import com.threeone.mealplanner.mapper.SeatStatusMapper;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.model.entity.SeatStatus;
import com.threeone.mealplanner.service.SeatService;

public class SeatServiceImpl implements SeatService {
	
	private static final Log LOG = LogFactory.getLog(SeatServiceImpl.class);
	
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
	
	
	public List<SeatInfo> getAvailableSeats(int restId, String dateDay,
			int dateClock, int peopleNum) throws InternalException {
		List<SeatInfo> availableSeats = seatInfoMapper.getAvailableSeatInfos(restId, dateDay, dateClock, peopleNum);
		return availableSeats;
	}

	
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

	
	public void occupySeat(int seatId, int restId, String dateDay,
			int dateClock, int peopleNum) throws InternalException {
		try {
			seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock, OCCUPIED);
			seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock + 1, OCCUPIED);
		} catch (Exception e) {
			String message = "Error to occupy of seatId = " + seatId + " with dateClock=" + dateClock + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
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

	
	public void reserveSeatById(int seatId, int restId, String dateDay,
			int dateClock) throws InternalException {
		try {
			seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock, RESERVED);
			seatStatusMapper.insertSeatStatus(seatId, restId, dateDay, dateClock + 1, RESERVED);
		} catch (Exception e) {
			String message = "Error to reserve of seatId = " + seatId + "with dateDay" + dateDay + " with dateClock=" + dateClock + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public void freeSeatById(int seatId, int restId, String dateDay,
			int dateClock) throws InternalException {
		try {
			seatStatusMapper.deleteBySeatIdClock(seatId, restId, dateDay, dateClock);
			seatStatusMapper.deleteBySeatIdClock(seatId, restId, dateDay, dateClock + 1);
		} catch (Exception e) {
			String message = "Error to free of seatId = " + seatId + "with dateDay" + dateDay + " with dateClock=" + dateClock + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public List<SeatInfo> getSeatsByRestId(int restId) throws InternalException {
		List<SeatInfo> seatInfos = seatInfoMapper.getSeatsByRestId(restId);
		return seatInfos;
	}

	
	public int addSeat(int restId, int seatNo, int peopleNum, String description)
			throws InternalException {
		try {
			SeatInfo seatInfo = new SeatInfo();
			seatInfo.setRestid(restId);
			seatInfo.setSeatno(seatNo);
			seatInfo.setPeoplenum(peopleNum);
			seatInfo.setDescription(description);		
			return seatInfoMapper.insert(seatInfo);
		} catch (Exception e) {
			String message = "Error to add seat of seatNo = " + seatNo  + "with peopleNum" + peopleNum + "with description" + description + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int updateSeat(int seatId, int restId, int seatNo, int peopleNum,
			String description) throws InternalException {
		try {
			SeatInfo seatInfo = new SeatInfo();
			seatInfo.setSeatid(seatId);
			seatInfo.setRestid(restId);
			seatInfo.setSeatno(seatNo);
			seatInfo.setPeoplenum(peopleNum);
			seatInfo.setDescription(description);
			return seatInfoMapper.updateByPrimaryKey(seatInfo);
		} catch (Exception e) {
			String message = "Error to update seat of seatId = " + seatId + "with restId" + restId + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int deleteSeat(int seatId, int restId) throws InternalException {
		try {
			return seatInfoMapper.deleteByPrimaryKey(seatId);
		} catch (Exception e) {
			String message = "Error to delete Seat of seatId = " + seatId + "with restId" + restId + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
}
