package com.threeone.mealplanner.controller.web;

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
@RequestMapping("/web/order")
public class WebOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/getOrderByRest")
	@ResponseBody
	public JsonResult<List<OrderDetail>> getOrderByRest(@RequestParam int restId, @RequestParam(defaultValue="null") String dateFrom, @RequestParam(defaultValue="null") String dateTo){
		Boolean flag = false;
		String message = "Get orderDetail of restId = " + restId + " from datefrom=" + dateFrom + " to dateTo=" + dateTo;
		try {
			List<OrderDetail> orderDetails = orderService.getOrderByRest(restId, dateFrom, dateTo);
			message += " success!";
			flag = true;
			return new JsonResult<List<OrderDetail>>(flag, message, orderDetails);
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
			return new JsonResult<List<OrderDetail>>(flag, message, null);
		}
	}
	
	@RequestMapping("/cancleByRest")
	@ResponseBody
	public JsonResult<String> cancleByRest(@RequestParam int orderId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId" + userId + " cancle the orderId=" + orderId;
		try {
			OrderInfo orderInfo = orderService.getOrderInfoById(orderId);
			orderInfo.setOperationuserid(userId);
			orderInfo.setStatus(OrderStatus.cancled.getValue());
			orderService.updateOrder(orderInfo);
			message += " success!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
	@RequestMapping("/confirmByRest")
	@ResponseBody
	public JsonResult<String> confirmByRest(@RequestParam int orderId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId" + userId + "confirm the orderId=" + orderId;
		try {
			OrderInfo orderInfo = orderService.getOrderInfoById(orderId);
			orderInfo.setOperationuserid(userId);
			orderInfo.setStatus(OrderStatus.comfirmed.getValue());
			orderService.updateOrder(orderInfo);
			message += " success!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
	@RequestMapping("/finish")
	@ResponseBody
	public JsonResult<String> finish(@RequestParam int orderId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId" + userId + "finish the orderId=" + orderId;
		try {
			OrderInfo orderInfo = orderService.getOrderInfoById(orderId);
			orderInfo.setOperationuserid(userId);
			orderInfo.setStatus(OrderStatus.finished.getValue());
			orderService.updateOrder(orderInfo);
			message += " success! Have a good meal!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}

}
