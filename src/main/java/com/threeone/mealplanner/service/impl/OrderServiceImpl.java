package com.threeone.mealplanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.MenuInfoMapper;
import com.threeone.mealplanner.mapper.OrderInfoMapper;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.OrderStatus;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.OrderInfo;
import com.threeone.mealplanner.service.MealService;
import com.threeone.mealplanner.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);
	
	private OrderInfoMapper orderInfoMapper;
	private MenuInfoMapper menuInfoMapper;
	
	@Autowired
	private MealService mealService;
	

	@Override
	public List<OrderDetail> getOrderByUser(int userId, int status)
			throws InternalException {
		try {
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			// 1. ��ȡ�û����еĶ�����Ϣ
			List<OrderInfo> orderInfos = orderInfoMapper.getOrderByUser(userId, status);
			// 2. ��ȡorder��ϸ��Ϣ
			for (OrderInfo orderInfo : orderInfos) {
				OrderDetail orderDetail = this.getOrderDetailByOrder(orderInfo);
				orderDetails.add(orderDetail);
			}
			LOG.info("Success to get orderDetail of userId = " + userId + " with status=" + status);
			return orderDetails;
		} catch (Exception e) {
			String message = "Error to get orderDetail of userId = " + userId + " with status=" + status + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		
	}

	@Override
	public List<OrderDetail> getOrderByRest(int restId, String dateFrom,
			String dateTo) throws InternalException {
		try {
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			// 1. ��ȡ�û����еĶ�����Ϣ
			List<OrderInfo> orderInfos = orderInfoMapper.getOrderByRest(restId, dateFrom, dateTo);
			// 2. ��ȡorder��ϸ��Ϣ
			for (OrderInfo orderInfo : orderInfos) {
				OrderDetail orderDetail = this.getOrderDetailByOrder(orderInfo);
				orderDetails.add(orderDetail);
			}
			LOG.info("Success to get orderDetail of restId = " + restId + " from datefrom=" + dateFrom + " to dateTo=" + dateTo);
			return orderDetails;
		} catch (Exception e) {
			String message = "Error to get orderDetail of restId = " + restId + " from datefrom=" + dateFrom + " to dateTo=" + dateTo + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	@Override
	public OrderDetail createOrder(OrderInfo orderInfo)
			throws InternalException {
		// 1.����ʱ�䣬restId,�������freeSeat��Id
		//����ȡʧ��
		orderInfo.setStatus(OrderStatus.commitFailed.getValue());
		
		// 2.�����ݿ��в�������
		// ��ȡ��λ�ɹ�
		orderInfo.setStatus(OrderStatus.commitSuccess.getValue());
		orderInfoMapper.insertSelective(orderInfo);
		// 3.�����Ӧ����ϸ��Ϣ
		return this.getOrderDetailByOrder(orderInfo);
	}

	@Override
	public int updateOrder(OrderInfo orderInfo) throws InternalException {
		try {
			orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
			LOG.info("Success to update orderID=" + orderInfo.getOrderid());
			return 0;
		} catch (Exception e) {
			String message = "Error to update orderID=" + orderInfo.getOrderid() + ". Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		
	}
	
	/**
	 * ����orderInfo��ȡ��ϸ��Ϣ
	 * @param orderInfo
	 * @return
	 */
	private OrderDetail getOrderDetailByOrder(OrderInfo orderInfo){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderInfo(orderInfo);
		// 1.����ÿ��������menuIds����ȡ��Ʒ��Ϣ
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		if (!StringUtils.isBlank(orderInfo.getMenuids())) {
			String[] menuIds = orderInfo.getMenuids().split(",");
			for (String menuId : menuIds) {
				int id = Integer.parseInt(menuId);
				MenuInfo menuInfo = menuInfoMapper.selectByPrimaryKey(id);
				menuInfos.add(menuInfo);
			}
		}
		orderDetail.setMenuInfos(menuInfos);
		// 2. ����mealId��ȡmeal��ϸ��Ϣ
		if (orderInfo.getMealid() != null) {
			try {
				orderDetail.setMealWithDetail(mealService.getMealDetail(orderInfo.getMealid()));
			} catch (InternalException e) {
				
			}
		}
		return orderDetail;
	}
	
	@Override
	public OrderInfo getOrderInfoById(int orderId) throws InternalException {
		try {
			return orderInfoMapper.selectByPrimaryKey(orderId);
		} catch (Exception e) {
			throw new InternalException(e.getMessage());
		}
	}

	public void setOrderInfoMapper(OrderInfoMapper orderInfoMapper) {
		this.orderInfoMapper = orderInfoMapper;
	}

	public void setMenuInfoMapper(MenuInfoMapper menuInfoMapper) {
		this.menuInfoMapper = menuInfoMapper;
	}


}
