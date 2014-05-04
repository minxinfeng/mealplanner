package com.threeone.mealplanner.mapper;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);
    
    //��֤�û�ע����Ϣ�Ƿ��Ѿ�����,����username,phonenum,email���ҽ���һ����Ϊ��
    UserInfo getUserInfoForCheck(@Param("username") String username, @Param("phonenum") String phonenum, @Param("email") String email);
    
    //��ȡ�û���¼�����Ϣ
    UserInfo getUserInfoByLogin(@Param("loginName") String loginName, @Param("password") String password);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}