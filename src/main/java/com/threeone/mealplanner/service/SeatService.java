package com.threeone.mealplanner.service;

import java.util.HashMap;
import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.entity.SeatInfo;

/**
 * ��λ״̬��Ϣ����
 * @author wangyongbo
 *
 */
public interface SeatService {

	/**
	 * ��ȡָ��ʱ��������������������Ŀ�����λ
	 * @return
	 * @throws InternalException 
	 */
	List<SeatInfo> getAvailableSeats(int restId, String dateDay, int dateClock, int peopleNum) throws InternalException;
	
	/**
	 * Ԥ��ָ��ʱ��������������������Ŀ�����λ
	 * @return
	 * @throws InternalException 
	 */
	SeatInfo reserveSeat(int restId, String dateDay, int dateClock, int peopleNum) throws InternalException;
	
	/**
	 * ռ��ָ��ʱ��������������������Ŀ�����λ
	 * @return
	 * @throws InternalException 
	 */
	void occupySeat(int seatId, int restId, String dateDay, int dateClock, int peopleNum) throws InternalException;
	
	/**
	 * �õ�ָ��������ĳһ��λ��һ�������ʱ�������״̬
	 * @return 
	 * @return
	 * @throws InternalException 
	 */
	HashMap<Integer, Integer> getStateOfSeatWholeDay(int seatId, int restId, String dateDay) throws InternalException;
}
