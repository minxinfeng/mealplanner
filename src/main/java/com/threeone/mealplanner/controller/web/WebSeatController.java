package com.threeone.mealplanner.controller.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			List<String> nextWeeks = getNextWeek();
			model.addAttribute("seatInfos", seatInfos);
			model.addAttribute("nextWeeks", nextWeeks);
			return "seat/seat.ftl";
		} catch (InternalException e) {
			e.printStackTrace();
			return "seat/seat.ftl";
		}
	}
	@RequestMapping(value="/getSeatStatusBySeatId", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<int[]> getSeatStatusBySeatId(@RequestParam int seatId, 
			@RequestParam int userId, @RequestParam String dateDay) {
		Boolean flag = false;
		String message = "Get seat status of userId = " + userId + " and seatId=" + seatId + " at day =" + dateDay;			
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			int[] status =  seatService.getStateOfSeatWholeDay(seatId, restId, dateDay);		
			message += " success!";
			flag = true;
			return new JsonResult<int[]>(flag, message, status);
		} catch (InternalException e) {
			message += "failed. Reason :" + e.getMessage();
			return new JsonResult<int[]>(flag, message, null);
		}
		
	}
	
	@RequestMapping(value="/changeSeatStatus", method = RequestMethod.POST)
	@ResponseBody
	public String changeSeatStatus(@RequestParam int[] submitHashmap, @RequestParam int seatId, @RequestParam int userId, @RequestParam String dateDay) {
		Boolean flag = false;
		String message = "Change seat status of userId = " + userId + " and seatId=" + seatId + " at day =" + dateDay ;			
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			for(int dateClock = 0; dateClock < submitHashmap.length; dateClock++){
				switch (submitHashmap[dateClock]) {
				case 0:
					seatService.freeSeatById(seatId, restId, dateDay, dateClock + 10);
					break;
				case 1:
					seatService.reserveSeatById(seatId, restId, dateDay, dateClock + 10);
					break;	
				case 2:
					seatService.occupySeat(seatId, restId, dateDay, dateClock + 10);
					break;
				}
			}
				
			message += " success!";
			flag = true;
			return message;
		} catch (InternalException e) {
			message += "failed. Reason :" + e.getMessage();
			return message;
		}
		
	}
	
	@RequestMapping(value="/changeSeatStatusById", method = RequestMethod.GET)
	@ResponseBody
	public String changeSeatStatusById(@RequestParam int seatId, @RequestParam int userId, @RequestParam String dateDay,
			@RequestParam int dateClock,@RequestParam int state) {
		Boolean flag = false;
		String message = "Change seat status of userId = " + userId + " and seatId=" + seatId + " at day =" + dateDay 
				+ " at clock " + dateClock + " for state " + state;			
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			
			switch (state) {
			case 0:
				seatService.freeSeatById(seatId, restId, dateDay, dateClock);
				break;
			case 1:
				seatService.reserveSeatById(seatId, restId, dateDay, dateClock);
				break;	
			case 2:
				seatService.occupySeat(seatId, restId, dateDay, dateClock);
				break;
			}			
				
			message += " success!";
			flag = true;
			return message;
		} catch (InternalException e) {
			message += "failed. Reason :" + e.getMessage();
			return message;
		}
		
	}
	
	private List<String> getNextWeek() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		List<String> dateStrings = new ArrayList();		
		for(int i = 0; i < 7; i++){
			date.setDate(date.getDate()+1);
			dateStrings.add(dateFormat.format(date).toString());
		}		
		return dateStrings;
	}
	
}
