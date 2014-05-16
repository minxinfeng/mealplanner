package com.threeone.mealplanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.MealFriendMapper;
import com.threeone.mealplanner.mapper.MealInfoMapper;
import com.threeone.mealplanner.mapper.RestaurantInfoMapper;
import com.threeone.mealplanner.mapper.UserInfoMapper;
import com.threeone.mealplanner.model.MealFriendStatus;
import com.threeone.mealplanner.model.MealRequestInfo;
import com.threeone.mealplanner.model.MealWithDetail;
import com.threeone.mealplanner.model.entity.MealFriend;
import com.threeone.mealplanner.model.entity.MealInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.push.PushService;
import com.threeone.mealplanner.service.MealService;

public class MealServiceImpl implements MealService {

	private static final Log LOG = LogFactory.getLog(MealServiceImpl.class);
	
	private MealInfoMapper mealInfoMapper;
	private MealFriendMapper mealFriendMapper;
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private PushService pushService;
	
	
	public int createMeal(MealInfo mealInfo, String friendIds) throws InternalException {
		try {
			// 1.创建mealinfo
			 mealInfoMapper.insertSelective(mealInfo);
			 mealInfo.setMealid(mealInfoMapper.getNewestMealId(mealInfo.getMealorganizeuserid()));
			 LOG.info("Create mealInfo menuId= " + mealInfo.getMealid());
			 UserInfo userInfo = userInfoMapper.getUserInfoById(mealInfo.getMealorganizeuserid());
			 String userName = userInfo.getUsername();
			// 2.向表mealfriend中添加相应的对应关系,friendIds用英文逗号分隔
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
			for (String string : ids) {
				int id = Integer.parseInt(string);
				pushService.setUserId(id);
				pushService.setTitle("饭局邀请");
				pushService.setDescription(userName + "喊你一起吃饭啦!快去看看有什么惊喜吧~");
				Thread thread = new Thread(pushService);
				thread.run();
				LOG.info("Send meal invitation to friend=" + id + " success!");
			}
			
			return 0;
		} catch (Exception e) {
			LOG.error("Fail to create meal info, reason:" + e.getMessage());
			throw new InternalException("Fail to create meal info, reason:" + e.getMessage());
		}
		
	}
	
	
	public List<MealWithDetail> getMealDetailByUserId(int userId, int status, int start, int limit)
			throws InternalException {
		try {
			List<MealWithDetail> mealWithDetailList = new ArrayList<MealWithDetail>(); 
			// 1.获取所有的meal信息
			List<MealInfo> mealInfos = mealInfoMapper.getMealListByUserId(userId, status, start, limit);
			// 2.获取每个对应的friend信息
			for (MealInfo mealInfo : mealInfos) {
				MealWithDetail mealWithDetail = new MealWithDetail();
				mealWithDetail.setMealInfo(mealInfo);
				mealWithDetail.setMealFriendWithStatusList(mealInfoMapper.getMealFriendWithStatus(mealInfo.getMealid()));
				mealWithDetailList.add(mealWithDetail);
			}
			LOG.info("Get mealinfo of userId=" + userId + " and status=" + status);
			return mealWithDetailList;
		} catch (Exception e) {
			String message = "Error to get the mealinfo of userId=" + userId + " and status=" + status + ", reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		
	}

	
	public List<MealRequestInfo> getMealRequest(int userId, int status)
			throws InternalException {
		try {
			List<MealRequestInfo> mealRequestInfos = new ArrayList<MealRequestInfo>();
			// 1.获取friendId=userId的所有menuId等相关信息
			List<MealFriend> mealFriends = mealFriendMapper.getMealRequestByUserId(userId, status);
			// 2.根据menuId获取mealInfo详细信息
			for (MealFriend mealFriend : mealFriends) {
				MealInfo mealInfo = mealInfoMapper.selectByPrimaryKey(mealFriend.getMealid());
				mealRequestInfos.add(mealInfo2MealRequestInfo(mealInfo,userId,mealFriend.getStatus()));
			}
			LOG.info("Get mealRequest of userId=" + userId + " and status=" + status);
			return mealRequestInfos;
		} catch (Exception e) {
			String message = "Error to get the mealRequest of userId=" + userId + " and status=" + status + ", reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public int handleAMeal(int mealId, int userId, int status) throws InternalException {
		try {
			int code = mealFriendMapper.handleAMeal(mealId, userId, status);
			MealInfo mealInfo = mealInfoMapper.selectByPrimaryKey(mealId);
			int organizedId = mealInfo.getMealorganizeuserid();
			UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
			String userName = userInfo.getUsername();
			pushService.setUserId(organizedId);
			pushService.setTitle("饭局邀请反馈");
			if(status == MealFriendStatus.accept.getValue()){
				pushService.setDescription(userName + "同意了你的饭局邀请:) 快去看看这个饭局的进展情况吧!");
			}else{
				pushService.setDescription(userName + "拒绝了你的饭局邀请:( 快去看看这个饭局的进展情况吧!");
			}
			Thread thread = new Thread(pushService);
			thread.run();
			LOG.info("Handle meal success!");
			return code;
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}

	
	public MealWithDetail getMealDetail(int mealId) throws InternalException {
		try {
			MealWithDetail mealWithDetail = new MealWithDetail();
			MealInfo mealInfo = mealInfoMapper.selectByPrimaryKey(mealId);
			mealWithDetail.setMealInfo(mealInfo);
			mealWithDetail.setMealFriendWithStatusList(mealInfoMapper.getMealFriendWithStatus(mealId));
			LOG.info("Get meal detail info of mealId=" + mealId);
			return mealWithDetail;
		} catch (Exception e) {
			LOG.info("Get meal detail info of mealId=" + mealId + " failed. Reason:" + e.getMessage());
			throw new InternalException(e.getMessage());
		}
	}
	
	//获取mealRequestInfo
	private MealRequestInfo mealInfo2MealRequestInfo(MealInfo mealInfo, int userId, int status){
		MealRequestInfo mealRequestInfo = new MealRequestInfo();
		mealRequestInfo.setMealid(mealInfo.getMealid());
		mealRequestInfo.setMealorganizeuserid(mealInfo.getMealorganizeuserid());
		mealRequestInfo.setMealorganizeusername(mealInfo.getMealorganizeusername());
		mealRequestInfo.setMealstatus(mealInfo.getMealstatus());
		mealRequestInfo.setMealtime(mealInfo.getMealtime());
		mealRequestInfo.setOrganizationtime(mealInfo.getOrganizationtime());
		mealRequestInfo.setRestid(mealInfo.getRestid());
		mealRequestInfo.setRestname(mealInfo.getRestname());
		mealRequestInfo.setStatus(status);
		mealRequestInfo.setUserid(userId);
		return mealRequestInfo;
	}

	public void setMealInfoMapper(MealInfoMapper mealInfoMapper) {
		this.mealInfoMapper = mealInfoMapper;
	}

	public void setMealFriendMapper(MealFriendMapper mealFriendMapper) {
		this.mealFriendMapper = mealFriendMapper;
	}

	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

}
