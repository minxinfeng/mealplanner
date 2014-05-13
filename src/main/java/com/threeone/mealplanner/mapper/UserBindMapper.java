package com.threeone.mealplanner.mapper;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.UserBind;

public interface UserBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBind record);

    int insertSelective(UserBind record);

    UserBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBind record);

    int updateByPrimaryKey(UserBind record);
    
    //根据userId获取绑定信息
    UserBind getUserBind(@Param("userId") int userId);
  //根据userId更新绑定信息
    int updateUserBind(UserBind userBind);
}