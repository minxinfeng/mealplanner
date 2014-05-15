package com.threeone.mealplanner.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.OrderStatus;
import com.threeone.mealplanner.model.entity.OrderInfo;
import com.threeone.mealplanner.service.OrderService;
import com.threeone.mealplanner.service.RestaurantService;

@Controller
@RequestMapping("/web/order")
public class WebOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value = "/getOrderByUserId", method = RequestMethod.GET)
	public String getOrderByUsrId(@RequestParam int userId, Model model){
		Boolean flag = false;
		String message = "Get orderDetail of userId = " + userId;
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			List<OrderDetail> orderDetails = orderService.getOrderByRest(restId, null, null);
			model.addAttribute("orderDetails", orderDetails);
			message += " success!";
			flag = true;
			return "order/order.ftl";
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
			return "order/order.ftl";
		}
	}
	
	@RequestMapping("/cancleByRest")
	@ResponseBody
	public JsonResult<String> cancleByRest(@RequestParam int orderId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId" + userId + " cancle the orderId=" + orderId;
		try {
			orderService.cancleOrder(orderId, userId);
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
			orderService.confirmOrder(orderId, userId);
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
