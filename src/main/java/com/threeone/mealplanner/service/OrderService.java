package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.entity.OrderInfo;

public interface OrderService {
	
	/**
	 * ��ȡuserId�����ж�����Ϣ
	 * @param userId
	 * @param status ����״̬��-1�������ж�  0�������ύ�ɹ� 1�������ύʧ��(����λ) 2���Ѿ����ͨ�� 3����ȡ�� 4�������
	 * @return
	 */
	List<OrderDetail> getOrderByUser(int userId, int status) throws InternalException;
	
	/**
	 * ��ȡĳ�������Ķ�����Ϣ
	 * @param restId
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	List<OrderDetail> getOrderByRest(int restId, String dateFrom, String dateTo) throws InternalException;
	
	/**
	 * ��������
	 * @param orderInfo
	 * @return
	 */
	OrderDetail createOrder(OrderInfo orderInfo) throws InternalException;
	
	/**
	 * ���¶���
	 * @param orderInfo
	 * @return
	 * @throws InternalException
	 */
	int updateOrder(OrderInfo orderInfo) throws InternalException;
	
	OrderInfo getOrderInfoById(int orderId) throws InternalException;
	
}
