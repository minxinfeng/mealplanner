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
     * ��ȡĳ�����ֵĺ�����Ϣ
     * @param mealId
     * @return
     */
    List<MealFriendWithStatus> getMealFriendWithStatus(@Param("mealId") int mealId);
    
    /**
     * ��ȡĳ�˴��������µ�mealId
     * @return
     */
    int getNewestMealId(@Param("userId") int userId);
    
    /**
     * ��ȡĳ������֯�����з�����Ϣ
     * @param userId
     * @return
     */
    List<MealInfo> getMealListByUserId(@Param("userId") int userId, @Param("mealstatus") int mealstatus);
    
}