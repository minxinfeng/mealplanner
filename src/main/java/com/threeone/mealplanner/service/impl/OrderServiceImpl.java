package com.threeone.mealplanner.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.MenuInfoMapper;
import com.threeone.mealplanner.mapper.OrderInfoMapper;
import com.threeone.mealplanner.mapper.RestaurantInfoMapper;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.OrderStatus;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.OrderInfo;
import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.push.PushService;
import com.threeone.mealplanner.service.MealService;
import com.threeone.mealplanner.service.OrderService;
import com.threeone.mealplanner.service.SeatService;

public class OrderServiceImpl implements OrderService {
	
	private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);
	
	private OrderInfoMapper orderInfoMapper;
	private MenuInfoMapper menuInfoMapper;
	private RestaurantInfoMapper restaurantInfoMapper;
	
	@Autowired
	private MealService mealService;
	
	@Autowired
	private SeatService seatService;
	@Autowired
	private PushService pushService;
	
	public List<OrderDetail> getOrderByUser(int userId, int status)
			throws InternalException {
		try {
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			// 1. 获取用户所有的订单信息
			List<OrderInfo> orderInfos = orderInfoMapper.getOrderByUser(userId, status);
			// 2. 获取order详细信息
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

	
	public List<OrderDetail> getOrderByRest(int restId, String dateFrom,
			String dateTo) throws InternalException {
		try {
			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			// 1. 获取用户所有的订单信息
			List<OrderInfo> orderInfos = orderInfoMapper.getOrderByRest(restId, dateFrom, dateTo);
			// 2. 获取order详细信息
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

	
	public OrderDetail createOrder(OrderInfo orderInfo) throws InternalException {
		try {
			// 1.根据时间，restId,人数获得freeSeat的Id
			int restId = orderInfo.getRestid();
			int peopleNum = orderInfo.getActualpeoplenum();
			Date mealTime = orderInfo.getMealtime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(mealTime);
			String dateDay = dateString.split(" ")[0];
			int dateClock = mealTime.getHours();
			List<SeatInfo> seatInfos = seatService.getAvailableSeats(restId, dateDay, dateClock, peopleNum);
			//若获取失败
			if(seatInfos == null){
				orderInfo.setStatus(OrderStatus.commitFailed.getValue());
				LOG.error("Sorry, No seats empty!");
				throw new InternalException("Create order failed for there is no seats empty!");
			}
			// 获取座位成功
			else {
				// 2.向数据库中插入数据
				orderInfo.setStatus(OrderStatus.commitSuccess.getValue());
				orderInfo.setSeatid(seatInfos.get(0).getSeatid());
				orderInfoMapper.insertSelective(orderInfo);
				seatService.reserveSeat(restId, dateDay, dateClock, peopleNum);
				LOG.info("create order success!");
				// 3.获得相应的详细信息
				orderInfo.setOrderid(orderInfoMapper.getNewestOrderIdByUser(orderInfo.getUserid()));
				return this.getOrderDetailByOrder(orderInfo);
			}
			
		} catch (Exception e) {
			String message = "error to create order, reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
		
	}

	public void confirmOrder(int orderId, int operationUserId) throws InternalException{
		try {
			OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
			int userId = orderInfo.getUserid();
			orderInfoMapper.updateOrderStatus(orderId, operationUserId, OrderStatus.comfirmed.getValue());
			
			pushService.setUserId(userId);
			pushService.setTitle("Order confirmed");
			pushService.setDescription("Your order " + orderId + " has confirmed by restanurant");
			Thread thread = new Thread(pushService);
			thread.run();
			LOG.info("Confirm order " + orderId);
		} catch (Exception e) {
			LOG.info("Confirm order " + orderId + " failed");
			throw new InternalException(e.getMessage());
		}
	}
	
	public void cancleOrder(int orderId, int userId) throws InternalException{
		try {
			String message = "Cancle order " + orderId + " by user " + userId;
			OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
			if(orderInfo.getStatus() == OrderStatus.comfirmed.getValue()){
				message += " failed! Your order has confirmed by the restaurant, please call and canlcle this order!";
				LOG.error(message);
				throw new InternalException(message);
			}else{
				orderInfoMapper.updateOrderStatus(orderId, userId, OrderStatus.cancled.getValue());
				message += " success!";
				LOG.info(message);
			}
		} catch (Exception e) {
			LOG.info("Cancle order " + orderId + " failed");
			throw new InternalException(e.getMessage());
		}
	}
	
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
	 * 根据orderInfo获取详细信息
	 * @param orderInfo
	 * @return
	 */
	private OrderDetail getOrderDetailByOrder(OrderInfo orderInfo){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderInfo(orderInfo);
		// 1.根据每个订单的menuIds，获取菜品信息
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
		// 2. 根据mealId获取meal详细信息
		if (orderInfo.getMealid() != -1) {
			try {
				orderDetail.setMealWithDetail(mealService.getMealDetail(orderInfo.getMealid()));
			} catch (InternalException e) {
				
			}
		}
		//3.获取restaurant信息
		RestaurantInfo restaurantInfo = restaurantInfoMapper.selectByPrimaryKey(orderInfo.getRestid());
		orderDetail.setRestaurantInfo(restaurantInfo);
		return orderDetail;
	}
	
	
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


	public void setRestaurantInfoMapper(RestaurantInfoMapper restaurantInfoMapper) {
		this.restaurantInfoMapper = restaurantInfoMapper;
	}


}
