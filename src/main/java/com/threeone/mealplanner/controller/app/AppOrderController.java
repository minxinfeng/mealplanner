package com.threeone.mealplanner.controller.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.OrderDetail;
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
	
	//创建饭局，若mealId为默认值时则直接创建饭局
	@RequestMapping("/createOrder")
	@ResponseBody
	public JsonResult<OrderDetail> createOrder(@RequestParam int userId, @RequestParam int restId, 
			@RequestParam(defaultValue="-1") int mealId, @RequestParam String date, @RequestParam int peopleNum,
			@RequestParam String menuIds, @RequestParam String phoneNum){
		Boolean flag = false;
		String message = "Create order for restId=" + restId;
		try {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date mealtime = format1.parse(date);
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setActualpeoplenum(peopleNum);
			orderInfo.setContactinfo(phoneNum);
			orderInfo.setMealid(mealId);
			orderInfo.setMealtime(mealtime);
			orderInfo.setMenuids(menuIds);
			orderInfo.setOperationuserid(userId);
			orderInfo.setRestid(restId);
			orderInfo.setUserid(userId);
			OrderDetail orderDetail = orderService.createOrder(orderInfo);
			message += " success!";
			flag = true;
			return new JsonResult<OrderDetail>(flag, message, orderDetail);
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
			return new JsonResult<OrderDetail>(flag, message, null);
		}
	}
	
	@RequestMapping("/cancleByUser")
	@ResponseBody
	public JsonResult<String> cancleByUser(@RequestParam int orderId, @RequestParam int userId){
		Boolean flag = false;
		String message = "userId=" + userId + " cancle the orderId=" + orderId;
		try {
			orderService.cancleOrder(orderId, userId);
			message += " success!";
			flag = true;
		} catch (Exception e) {
			message = message + " failed. Reason:" + e.getMessage();
		}
		return new JsonResult<String>(flag, message, null);
	}
	
}
