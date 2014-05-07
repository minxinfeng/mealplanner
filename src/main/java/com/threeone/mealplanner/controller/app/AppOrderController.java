package com.threeone.mealplanner.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.OrderStatus;
import com.threeone.mealplanner.model.entity.OrderInfo;
import com.threeone.mealplanner.service.OrderService;

@Controller
@RequestMapping("/app/order")
public class AppOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/getOrderByUser")
	@ResponseBody
	public JsonResult<List<OrderDetail>> getOrderByUser(@RequestParam int userId, @RequestParam(defaultValue="-1") int status){
		Boolean flag = false;
		String message = " Get orderDetail of userId = " + userId + " with status=" + status;
		try {
			List<OrderDetail> orderDetails = orderService.getOrderByUser(userId, status);
			message += " success!";
			flag = true;
			return new JsonResult<List<OrderDetail>>(flag, message, orderDetails);
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
			return new JsonResult<List<OrderDetail>>(flag, message, null);
		}
	}
	
	@RequestMapping("/createOrderByMeal")
	@ResponseBody
	public JsonResult<String> createOrderByMeal(@RequestParam int mealId){
		Boolean flag = false;
		String message = "Create order of mealId=" + mealId;
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setMealid(mealId);
			orderService.createOrder(orderInfo);
			message += " success!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
	@RequestMapping("/createOrder")
	@ResponseBody
	public JsonResult<String> createOrder(@RequestParam int restId){
		Boolean flag = false;
		String message = "Create order for restId=" + restId;
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderService.createOrder(orderInfo);
			message += " success!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
	@RequestMapping("/cancleByUser")
	@ResponseBody
	public JsonResult<String> cancleByUser(@RequestParam int orderId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId=" + userId + " cancle the orderId=" + orderId;
		try {
			OrderInfo orderInfo = orderService.getOrderInfoById(orderId);
			if(orderInfo.getStatus() == OrderStatus.comfirmed.getValue()){
				message += " failed! Your order has confirmed by the restaurant, please call and canlcle this order!";
			}else{
				orderInfo.setOperationuserid(userId);
				orderInfo.setStatus(OrderStatus.cancled.getValue());
				orderService.updateOrder(orderInfo);
				message += " success!";
				flag = true;
			}
			
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
}
