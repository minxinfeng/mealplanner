package com.threeone.mealplanner.service;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.UserInfoWithFriends;

public interface FriendService {
	
	/**
	 * friendId��ȡ���������б�
	 * @param friendId����ӵĺ���ID
	 * @param status ������״̬
	 * @return
	 * @throws InternalException
	 */
	UserInfoWithFriends getFriendRequestsByStatus(int friendId, int status) throws InternalException;
	
	/**
	 * ��ȡuserId�ĺ����б�
	 * @param userId
	 * @return
	 * @throws InternalException
	 */
	UserInfoWithFriends getAllFriendInfos(int userId) throws InternalException;
	
	/**
	 * userId��friendId���ͺ�������
	 * @param userId
	 * @param friendId
	 * @return
	 * @throws InternalException
	 */
	int addFriendRequest(int userId, int friendId) throws InternalException;
	
	/**
	 * friendId����userId�ĺ�������
	 * @param friendId
	 * @param userId
	 * @param status ����������״̬��0����������; 1: friendId�Ѿ�ͬ��; 2: friendId�ܾ�
	 * @return
	 * @throws InternalException
	 */
	int handleFriendRequest(int friendId, int userId, int status) throws InternalException;

}
