package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.MealFriendWithStatus;
import com.threeone.mealplanner.model.entity.MealInfo;

public interface MealInfoMapper {
    int deleteByPrimaryKey(Integer mealid);

    int insert(MealInfo record);

    int insertSelective(MealInfo record);

    MealInfo selectByPrimaryKey(Integer mealid);

    int updateByPrimaryKeySelective(MealInfo record);

    int updateByPrimaryKey(MealInfo record);
    
    /**
     * 获取某个饭局的好友信息
     * @param mealId
     * @return
     */
    List<MealFriendWithStatus> getMealFriendWithStatus(@Param("mealId") int mealId);
    
    /**
     * 获取某人创建的最新的mealId
     * @return
     */
    int getNewestMealId(@Param("userId") int userId);
    
    /**
     * 获取某个人组织的所有饭局信息
     * @param userId
     * @return
     */
    List<MealInfo> getMealListByUserId(@Param("userId") int userId, @Param("mealstatus") int mealstatus);
    
}