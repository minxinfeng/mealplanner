package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.MenuInfo;

public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
    
    /**
     * ����restId��ȡ�����Ĳ˵���Ϣ
     * @param restId
     * @return
     */
    List<MenuInfo> getMenuByRestId(@Param("restId") int restId);
    
    /**
     * ��ȡĳ�������в˵���Ϣ
     * @param typeId
     * @return
     */
    List<MenuInfo> getMenuInfoByType(@Param("typeId") int typeId);
}