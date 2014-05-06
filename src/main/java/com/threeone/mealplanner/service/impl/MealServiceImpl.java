package com.threeone.mealplanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.MealFriendMapper;
import com.threeone.mealplanner.mapper.MealInfoMapper;
import com.threeone.mealplanner.model.MealFriendStatus;
import com.threeone.mealplanner.model.MealWithFriends;
import com.threeone.mealplanner.model.entity.MealFriend;
import com.threeone.mealplanner.model.entity.MealInfo;
import com.threeone.mealplanner.service.MealService;

public class MealServiceImpl implements MealService {

	private static final Log LOG = LogFactory.getLog(MealServiceImpl.class);
	
	private MealInfoMapper mealInfoMapper;
	private MealFriendMapper mealFriendMapper;
	
	@Override
	public int createMeal(MealInfo mealInfo, String friendIds) throws InternalException {
		try {
			
			// 1.����mealinfo
			 mealInfoMapper.insertSelective(mealInfo);
			 mealInfo.setMealid(mealInfoMapper.getNewestMealId());
			 LOG.info("Create mealInfo menuId= " + mealInfo.getMealid());
			// 2.���mealfriend�������Ӧ�Ķ�Ӧ��ϵ,friendIds��Ӣ�Ķ��ŷָ�
			String[] ids = friendIds.split(",");
			for (String string : ids) {
				int id = Integer.parseInt(string);
				MealFriend mealFriend = new MealFriend();
				mealFriend.setMealid(mealInfo.getMealid());
				mealFriend.setFriendid(id);
				mealFriend.setStatus(MealFriendStatus.waiting.getValue());
				mealFriendMapper.insertSelective(mealFriend);
				LOG.info("Create mealInfo's friend = " + id + " where mealId=" + mealInfo.getMealid());
			}
			return 0;
		} catch (Exception e) {
			LOG.error("Fail to create meal info, reason:" + e.getMessage());
			throw new InternalException("Fail to create meal info, reason:" + e.getMessage());
		}
		
	}
	
	@Override
	public List<MealWithFriends> getMealInfoByUserId(int userId, int status)
			throws InternalException {
		try {
			List<MealWithFriends> mealWithFriendsList = new ArrayList<MealWithFriends>(); 
			// 1.��ȡ���е�meal��Ϣ
			List<MealInfo> mealInfos = mealInfoMapper.getMealListByUserId(userId, status);
			// 2.��ȡÿ����Ӧ��friend��Ϣ
			for (MealInfo mealInfo : mealInfos) {
				MealWithFriends mealWithFriends = new MealWithFriends();
				mealWithFriends.setMealInfo(mealInfo);
				mealWithFriends.setMealFriendWithStatusList(mealInfoMapper.getMealFriendWithStatus(mealInfo.getMealid()));
				mealWithFriendsList.add(mealWithFriends);
			}
			LOG.info("Get mealinfo of userId=" + userId + " and status=" + status);
			return mealWithFriendsList;
		} catch (Exception e) {
			String message = "Error to get the mealinfo of userId=" + userId + " and status=" + status + ", reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		
	}

	@Override
	public List<MealInfo> getMealRequest(int userId, int status)
			throws InternalException {
		try {
			List<MealInfo> mealInfos = new ArrayList<MealInfo>();
			// 1.��ȡfriendId=userId������menuId
			List<Integer> mealIds = mealFriendMapper.getMealRequestByUserId(userId, status);
			// 2.����menuId��ȡmealInfo��ϸ��Ϣ
			for (Integer mealId : mealIds) {
				MealInfo mealInfo = mealInfoMapper.selectByPrimaryKey(mealId);
				mealInfos.add(mealInfo);
			}
			LOG.info("Get mealRequest of userId=" + userId + " and status=" + status);
			return mealInfos;
		} catch (Exception e) {
			String message = "Error to get the mealRequest of userId=" + userId + " and status=" + status + ", reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	@Override
	public int handleAMeal(int mealId, int userId, int status) throws InternalException {
		try {
			return mealFriendMapper.handleAMeal(mealId, userId, status);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}

	@Override
	public MealWithFriends getMealInfoFriends(int mealId) throws InternalException {
		try {
			MealWithFriends mealWithFriends = new MealWithFriends();
			MealInfo mealInfo = mealInfoMapper.selectByPrimaryKey(mealId);
			mealWithFriends.setMealInfo(mealInfo);
			mealWithFriends.setMealFriendWithStatusList(mealInfoMapper.getMealFriendWithStatus(mealId));
			LOG.info("Get meal detail info of mealId=" + mealId);
			return mealWithFriends;
		} catch (Exception e) {
			LOG.info("Get meal detail info of mealId=" + mealId + " failed. Reason:" + e.getMessage());
			throw new InternalException(e.getMessage());
		}
	}

	public void setMealInfoMapper(MealInfoMapper mealInfoMapper) {
		this.mealInfoMapper = mealInfoMapper;
	}

	public void setMealFriendMapper(MealFriendMapper mealFriendMapper) {
		this.mealFriendMapper = mealFriendMapper;
	}

}
