package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.MealRequestInfo;
import com.threeone.mealplanner.model.MealWithDetail;
import com.threeone.mealplanner.model.entity.MealInfo;

/**
 * 组饭局相关
 * @author fengxiangmin
 *
 */
public interface MealService {
	
	/**
	 * 创建一个饭局
	 * @param mealInfo
	 * @param friendIds
	 * @return
	 * @throws InternalException
	 */
	int createMeal(MealInfo mealInfo, String friendIds) throws InternalException;
	
	/**
	 * 获取某个人组织的饭局
	 * @param userId
	 * @param status
	 * @param start
	 * @param limit 默认为5
	 * @return
	 * @throws InternalException
	 */
	List<MealWithDetail> getMealDetailByUserId(int userId, int status, int start, int limit) throws InternalException;
	
	/**
	 * 获取某个人待处理的饭局信息
	 * @param userId
	 * @param status
	 * @return
	 * @throws InternalException
	 */
	List<MealRequestInfo> getMealRequest(int userId, int status) throws InternalException;
	/**
	 * 处理饭局邀请
	 * @param mealId
	 * @param userId
	 * @param status 同意或拒绝或等待处理
	 * @return
	 * @throws InternalException 
	 */
	int handleAMeal(int mealId, int userId, int status) throws InternalException;
	
	/**
	 * 获取饭局中的详细信息
	 * @param mealId
	 * @return
	 * @throws InternalException 
	 */
	MealWithDetail getMealDetail(int mealId) throws InternalException;
	
}
