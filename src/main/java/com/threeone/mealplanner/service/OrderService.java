package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.entity.OrderInfo;

public interface OrderService {
	
	/**
	 * 获取userId的所有订单信息
	 * @param userId
	 * @param status 订单状态：-1则是所有订  0：订单提交成功 1：订单提交失败(无座位) 2：已经审核通过 3：被取消 4：已完成
	 * @return
	 */
	List<OrderDetail> getOrderByUser(int userId, int status) throws InternalException;
	
	/**
	 * 获取某个餐厅的订单信息
	 * @param restId
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	List<OrderDetail> getOrderByRest(int restId, String dateFrom, String dateTo) throws InternalException;
	
	/**
	 * 创建订单
	 * @param orderInfo
	 * @return
	 */
	OrderDetail createOrder(OrderInfo orderInfo) throws InternalException;
	
	/**
	 * 更新订单
	 * @param orderInfo
	 * @return
	 * @throws InternalException
	 */
	int updateOrder(OrderInfo orderInfo) throws InternalException;
	
	OrderInfo getOrderInfoById(int orderId) throws InternalException;
	
}
