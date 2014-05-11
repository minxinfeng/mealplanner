package com.threeone.mealplanner.controller.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.SeatService;

@Controller
@RequestMapping("/web/seat")
public class WebSeatController {
	
	@Autowired	
	private SeatService seatService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value="/getSeatByUserId", method = RequestMethod.GET)
	public String getSeatByUserId(@RequestParam int userId, Model model){
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			List<SeatInfo> seatInfos = seatService.getSeatsByRestId(restId);
			model.addAttribute("seatInfos", seatInfos);
			return "seat/seat.ftl";
		} catch (InternalException e) {
			e.printStackTrace();
			return "seat/seat.ftl";
		}
	}
	@RequestMapping(value="/getSeatStatusBySeatId", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<HashMap<Integer, Integer>> getSeatStatusBySeatId(@RequestParam int seatId, 
			@RequestParam int userId, @RequestParam String dateDay) {
		Boolean flag = false;
		String message = "Get seat status of userId = " + userId + " and seatId=" + seatId + " at day =" + dateDay;	
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			HashMap<Integer, Integer> status =  seatService.getStateOfSeatWholeDay(seatId, restId, dateDay);
			message += " success!";
			flag = true;
			return new JsonResult<HashMap<Integer,Integer>>(flag, message, status);
		} catch (InternalException e) {
			message += "failed. Reason :" + e.getMessage();
			return new JsonResult<HashMap<Integer,Integer>>(flag, message, null);
		}
		
	}
}
