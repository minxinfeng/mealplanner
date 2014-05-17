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
     * ���Ѵ�����ķ���״̬
     * @param mealId
     * @param userId
     * @param status
     * @return
     */
    int handleAMeal(@Param("mealId") int mealId, @Param("userId") int userId, @Param("status") int status);
    
    /**
     * ��ȡ���˽��յ�����������
     * @param userId
     * @param status
     * @return
     */
    List<MealFriend> getMealRequestByUserId( @Param("userId") int userId, @Param("status") int status);
    
    //��ȡ�÷���ͬ��ĺ�����
    int getAcceptNum(@Param("mealId") int mealId);
    //��ȡ�÷��־ܾ��ĺ�����
    int getRejectNum(@Param("mealId") int mealId);
    //��ȡ���к�����
    int getAllNum(@Param("mealId") int mealId);
}