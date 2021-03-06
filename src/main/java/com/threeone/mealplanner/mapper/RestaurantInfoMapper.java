package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.RestInfoForMap;
import com.threeone.mealplanner.model.entity.RestaurantInfo;

public interface RestaurantInfoMapper {
    int deleteByPrimaryKey(Integer restid);

    int insert(RestaurantInfo record);

    int insertSelective(RestaurantInfo record);

    RestaurantInfo selectByPrimaryKey(Integer restid);

    int updateByPrimaryKeySelective(RestaurantInfo record);

    int updateByPrimaryKey(RestaurantInfo record);
    
    List<RestaurantInfo> getAllRestaurantInfos();
    
    List<RestaurantInfo> getSeveralRestaurantInfos(@Param("startFrom") int startFrom, @Param("limit") int limit);
    
    RestaurantInfo getRestInfoByExactName(@Param("restName") String restName);
    
    List<RestaurantInfo> getRestsByName(@Param("restName") String restName);
    
    //通过餐厅的名称精确匹配获取餐厅基本信息(restId, restName, longitude, latitude)
    List<RestInfoForMap> getRestInfoForMaps(@Param("restName") String restName);
}