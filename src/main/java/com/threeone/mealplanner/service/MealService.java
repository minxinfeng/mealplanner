package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.MealRequestInfo;
import com.threeone.mealplanner.model.MealWithDetail;
import com.threeone.mealplanner.model.entity.MealInfo;

/**
 * �鷹�����
 * @author fengxiangmin
 *
 */
public interface MealService {
	
	/**
	 * ����һ������
	 * @param mealInfo
	 * @param friendIds
	 * @return
	 * @throws InternalException
	 */
	int createMeal(MealInfo mealInfo, String friendIds) throws InternalException;
	
	/**
	 * ��ȡĳ������֯�ķ���
	 * @param userId
	 * @param status
	 * @param start
	 * @param limit Ĭ��Ϊ5
	 * @return
	 * @throws InternalException
	 */
	List<MealWithDetail> getMealDetailByUserId(int userId, int status, int start, int limit) throws InternalException;
	
	/**
	 * ��ȡĳ���˴�����ķ�����Ϣ
	 * @param userId
	 * @param status
	 * @return
	 * @throws InternalException
	 */
	List<MealRequestInfo> getMealRequest(int userId, int status) throws InternalException;
	/**
	 * ����������
	 * @param mealId
	 * @param userId
	 * @param status ͬ���ܾ���ȴ�����
	 * @return
	 * @throws InternalException 
	 */
	int handleAMeal(int mealId, int userId, int status) throws InternalException;
	
	/**
	 * ��ȡ�����е���ϸ��Ϣ
	 * @param mealId
	 * @return
	 * @throws InternalException 
	 */
	MealWithDetail getMealDetail(int mealId) throws InternalException;
	
}
