package com.threeone.mealplanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.FriendInfoMapper;
import com.threeone.mealplanner.mapper.FriendRequestStatus;
import com.threeone.mealplanner.mapper.UserInfoMapper;
import com.threeone.mealplanner.model.UserInfoWithFriends;
import com.threeone.mealplanner.model.entity.FriendInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.FriendService;

public class FriendServiceImpl implements FriendService {

	private static final Log LOG = LogFactory.getLog(FriendServiceImpl.class);
	
	private FriendInfoMapper friendInfoMapper;
	private UserInfoMapper userInfoMapper;
	
	
	public UserInfoWithFriends getFriendRequestsByStatus(int friendId, int status) throws InternalException {
		try {
			UserInfoWithFriends userInfoWithFriends = new UserInfoWithFriends();
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			userInfoWithFriends.setUserId(friendId);
			userInfoWithFriends.setStatus(status);
			List<Integer> ids = friendInfoMapper.getFriendRequestIdsByStatus(friendId, status);
			for (Integer id : ids) {
				UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
				userInfos.add(userInfo);
			}
			userInfoWithFriends.setUserInfos(userInfos);
			LOG.info("Get friend request list of userid=" + friendId + " and status=" + status);
			return userInfoWithFriends;
		} catch (Exception e) {
			LOG.error("Get friend request list of userid=" + friendId + " error, reason:" + e.getMessage());
			throw new InternalException(e.getMessage());
		}
	}

	
	public UserInfoWithFriends getAllFriendInfos(int userId) throws InternalException {
		try {
			List<Integer> friendIds = friendInfoMapper.getAllFriendIds(userId);
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			UserInfoWithFriends userInfoWithFriends = new UserInfoWithFriends();
			userInfoWithFriends.setUserId(userId);
			userInfoWithFriends.setStatus(FriendRequestStatus.accept.getValue());
			for (Integer id : friendIds) {
				UserInfo userInfo = new UserInfo();
				userInfo = userInfoMapper.selectByPrimaryKey(id);
				userInfos.add(userInfo);
			}
			userInfoWithFriends.setUserInfos(userInfos);
			LOG.info("Get all friend list of userid=" + userId);
			return userInfoWithFriends;
		} catch (Exception e) {
			LOG.error("Get all friend list of userid=" + userId + " failed, reason:" + e.getMessage());
			throw new InternalException(e.getMessage());
		}
	}

	
	public int addFriendRequest(int userId, int friendId)
			throws InternalException {
		try {
			FriendInfo friendInfo = new FriendInfo();
			friendInfo.setUserid(userId);
			friendInfo.setFriendid(friendId);
			friendInfo.setStatus(FriendRequestStatus.requesting.getValue());
			LOG.info("userId=" + userId + " add friendId=" + friendId);
			return friendInfoMapper.insertSelective(friendInfo);
		} catch (Exception e) {
			LOG.error("userId=" + userId + " add friendId=" + friendId + " error! reason:" + e.getMessage());
			throw new InternalException(e.getMessage());
		}
	}

	
	public int handleFriendRequest(int friendId, int userId, int status)
			throws InternalException {
		try {
			LOG.info("userId=" + friendId + " handle friendId=" + userId + " request to status=" + status);
			return friendInfoMapper.handleFriendRequest(friendId, userId, status);
		} catch (Exception e) {
			LOG.info("userId=" + friendId + " handle friendId=" + userId + " request to status=" + status + " error! Reason:" + e.getMessage());
			throw new InternalException(e.getMessage());
		}
	}

	public void setFriendInfoMapper(FriendInfoMapper friendInfoMapper) {
		this.friendInfoMapper = friendInfoMapper;
	}

	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

}
