package com.threeone.mealplanner.service;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.UserInfoWithFriends;

public interface FriendService {
	
	/**
	 * friendId获取好友请求列表
	 * @param friendId被添加的好友ID
	 * @param status 请求处理状态
	 * @return
	 * @throws InternalException
	 */
	UserInfoWithFriends getFriendRequestsByStatus(int friendId, int status) throws InternalException;
	
	/**
	 * 获取userId的好友列表
	 * @param userId
	 * @return
	 * @throws InternalException
	 */
	UserInfoWithFriends getAllFriendInfos(int userId) throws InternalException;
	
	/**
	 * userId向friendId发送好友请求
	 * @param userId
	 * @param friendId
	 * @return
	 * @throws InternalException
	 */
	int addFriendRequest(int userId, int friendId) throws InternalException;
	
	/**
	 * friendId处理userId的好友请求
	 * @param friendId
	 * @param userId
	 * @param status 好友请求处理状态：0好友请求中; 1: friendId已经同意; 2: friendId拒绝
	 * @return
	 * @throws InternalException
	 */
	int handleFriendRequest(int friendId, int userId, int status) throws InternalException;

}
