package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.MealFriend;

public interface MealFriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MealFriend record);

    int insertSelective(MealFriend record);

    MealFriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MealFriend record);

    int updateByPrimaryKey(MealFriend record);
    
    /**
     * 好友处理更改饭局状态
     * @param mealId
     * @param userId
     * @param status
     * @return
     */
    int handleAMeal(@Param("mealId") int mealId, @Param("userId") int userId, @Param("status") int status);
    
    /**
     * 获取本人接收到的所有邀请
     * @param userId
     * @param status
     * @return
     */
    List<MealFriend> getMealRequestByUserId( @Param("userId") int userId, @Param("status") int status);
    
    //获取该饭局同意的好友数
    int getAcceptNum(@Param("mealId") int mealId);
    //获取该饭局拒绝的好友数
    int getRejectNum(@Param("mealId") int mealId);
    //获取所有好友数
    int getAllNum(@Param("mealId") int mealId);
}