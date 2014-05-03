package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.FriendInfo;

public interface FriendInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FriendInfo record);

    int insertSelective(FriendInfo record);

    FriendInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendInfo record);

    int updateByPrimaryKey(FriendInfo record);
}