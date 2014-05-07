package com.threeone.mealplanner.service;

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
}
