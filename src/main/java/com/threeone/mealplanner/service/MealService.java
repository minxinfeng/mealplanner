package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.MealWithFriends;
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
	 * @return
	 * @throws InternalException
	 */
	List<MealWithFriends> getMealInfoByUserId(int userId, int status) throws InternalException;
	
	/**
	 * ��ȡĳ���˴�����ķ�����Ϣ
	 * @param userId
	 * @param status
	 * @return
	 * @throws InternalException
	 */
	List<MealInfo> getMealRequest(int userId, int status) throws InternalException;
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
	 * ��ȡ�����еĺ��������Ϣ
	 * @param mealId
	 * @return
	 * @throws InternalException 
	 */
	MealWithFriends getMealInfoFriends(int mealId) throws InternalException;
	
}
