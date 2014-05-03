package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.MenuInfo;

public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
}