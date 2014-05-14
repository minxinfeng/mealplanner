package com.threeone.mealplanner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.entity.OrderInfo;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
    
    List<OrderInfo> getOrderByUser(@Param("userId") int userId, @Param("status") int status);
    
    List<OrderInfo> getOrderByRest(@Param("restId") int restId, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
        //获取用户最新提交的订单ID
    int getNewestOrderIdByUser(@Param("userId") int userId);
    
    int updateOrderStatus(@Param("orderId") int orderId, @Param("operationUserId") int operationUserId, @Param("status") int status);
}