package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.FriendInfo;

public interface FriendInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendInfo record);

    int insertSelective(FriendInfo record);

    FriendInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendInfo record);

    int updateByPrimaryKey(FriendInfo record);
    
    List<Integer> getFriendRequestIdsByStatus(@Param("friendId") int friendId, @Param("status") int status);
    
    List<Integer> getAllFriendIds(@Param("userId") int userId);
    
    int handleFriendRequest(@Param("friendId") int friendId, @Param("userId") int userId, @Param("status") int status);
}