package com.threeone.mealplanner.mapper;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);
    
    //验证用户注册信息是否已经存在,其中username,phonenum,email有且仅有一个不为空
    UserInfo getUserInfoForCheck(@Param("username") String username, @Param("phonenum") String phonenum, @Param("email") String email);
    
    //获取用户登录相关信息
    UserInfo getUserInfoByLogin(@Param("loginName") String loginName, @Param("password") String password);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}