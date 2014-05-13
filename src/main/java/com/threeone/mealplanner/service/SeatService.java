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
	 * Ԥ��ָ��ʱ���ָ����λ
	 * @return
	 * @throws InternalException 
	 */
	void reserveSeatById(int seatId, int restId, String dateDay, int dateClock) throws InternalException;
	
	/**
	 * ռ��ָ��ʱ��������������������Ŀ�����λ
	 * @return
	 * @throws InternalException 
	 */
	void occupySeat(int seatId, int restId, String dateDay, int dateClock) throws InternalException;
	
	/**
	 * �ͷ�ָ��ʱ���ָ����λ
	 * @return
	 * @throws InternalException 
	 */
	void freeSeatById(int seatId, int restId, String dateDay, int dateClock) throws InternalException;
	
	/**
	 * �õ�ָ��������ĳһ��λ��һ�������ʱ�������״̬
	 * @return 
	 * @return
	 * @throws InternalException 
	 */
	int[] getStateOfSeatWholeDay(int seatId, int restId, String dateDay) throws InternalException;
	
	/**
	 * ��ȡĳ������������λ��Ϣ
	 * @return
	 * @throws InternalException 
	 */
	List<SeatInfo> getSeatsByRestId(int restId) throws InternalException;
	
	/**
	 * ���������λ
	 * @return
	 * @throws InternalException 
	 */
	int addSeat(int restId, int seatNo, int peopleNum, String description) throws InternalException;
	
	/**
	 * ����������λ��Ϣ
	 * @return
	 * @throws InternalException 
	 */
	int updateSeat(int seatId, int restId, int seatNo, int peopleNum, String description) throws InternalException;
	
	/**
	 * ����ɾ����λ��Ϣ
	 * @return
	 * @throws InternalException 
	 */
	int deleteSeat(int seatId) throws InternalException;
}
