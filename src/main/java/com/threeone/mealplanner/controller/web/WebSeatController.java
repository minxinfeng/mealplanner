package com.threeone.mealplanner.controller.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.OrderDetail;
import com.threeone.mealplanner.model.entity.MenuInfo;
import com.threeone.mealplanner.model.entity.RestType;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.SeatService;
import com.threeone.mealplanner.service.UserService;

@Controller
@RequestMapping("/web/seat")
public class WebSeatController {
	
	@Autowired	
	private SeatService seatService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getSeatByUserId", method = RequestMethod.GET)
	public String getSeatByUserId(@RequestParam int userId, Model model, HttpSession session){
		try {
			if(session.getAttribute("userId") == null){
				return "redirect:/web/login.ftl";
			}
			UserInfo checkUserInfo = userService.getUserInfoById(Integer.parseInt(session.getAttribute("userId").toString()));
			if(checkUserInfo != null){
				int restId = restaurantService.getRestIdByUserId(userId);
				List<SeatInfo> seatInfos = seatService.getSeatsByRestId(restId);
				List<String> nextWeeks = getNextWeek();
				model.addAttribute("seatInfos", seatInfos);
				model.addAttribute("nextWeeks", nextWeeks);
				return "seat/seat.ftl";
			}else{
				System.out.println("user is null");
				return "redirect:/web/login.ftl";
			}
			
		} catch (InternalException e) {
			e.printStackTrace();
			return "seat/seat.ftl";
		}
	}
	@RequestMapping(value = "/getSeatStatusBySeatId", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/changeSeatStatus", method = RequestMethod.POST)
	@ResponseBody
	public String changeSeatStatus(@RequestParam int[] submitHashmap, @RequestParam int seatId, @RequestParam int userId, @RequestParam String dateDay) {
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
			return message;
		} catch (InternalException e) {
			message += "failed. Reason :" + e.getMessage();
			return message;
		}
		
	}
	
	@RequestMapping(value = "/changeSeatStatusById", method = RequestMethod.GET)
	@ResponseBody
	public String changeSeatStatusById(@RequestParam int seatId, @RequestParam int userId, @RequestParam String dateDay,
			@RequestParam int dateClock,@RequestParam int state) {
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
			return message;
		} catch (InternalException e) {
			message += "failed. Reason :" + e.getMessage();
			return message;
		}
	}
	
	@RequestMapping(value = "/getSeatInfosByUserId", method = RequestMethod.GET)
	public String getSeatInfosByUserId(@RequestParam int userId, Model model, HttpSession session){
		String message = "Get seats for userId=" + userId;
		try {
			if(session.getAttribute("userId") == null){
				return "redirect:/web/login.ftl";
			}
			UserInfo checkUserInfo = userService.getUserInfoById(Integer.parseInt(session.getAttribute("userId").toString()));
			if(checkUserInfo != null){
				int restId = restaurantService.getRestIdByUserId(userId);
				List<SeatInfo> seatInfos = seatService.getSeatsByRestId(restId);
				System.out.println(seatInfos.size());
				model.addAttribute("seatInfos", seatInfos);
				message += " success!";
				return "seat/seatManager.ftl";
			}else{
				System.out.println("user is null");
				return "redirect:/web/login.ftl";
			}		
			
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return "seat/seatManager.ftl";
		}
	}
	
	@RequestMapping(value = "/addSeat", method = RequestMethod.GET)
	public String addSeat(@RequestParam int userId, @RequestParam int seatNo, @RequestParam String description,
			@RequestParam int peopleNum){
		System.out.println("add seat");
		String message = "Add seat of userId=" + userId;
		String defaultDes = "no description";
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			if("".equals(description)){
				description = defaultDes;
			}	
			seatService.addSeat(restId, seatNo, peopleNum, description);			
			message += " success!";
			return "redirect:getSeatInfosByUserId?userId=" + userId;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return "redirect:getSeatInfosByUserId?userId=" + userId;
		}
	}
	
	@RequestMapping(value = "/deleteSeat", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSeat(@RequestParam int seatId){
		String message = "delete seat of seatId=" + seatId;
		try {
			seatService.deleteSeat(seatId);
			message += " success!";
			return message;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return message;
		}
	}
	
	@RequestMapping(value = "/updateSeat", method = RequestMethod.GET)
	public String updateSeat(@RequestParam int seatId, @RequestParam int userId, @RequestParam int seatNo, @RequestParam(defaultValue="Nothing to describe") String description,
			@RequestParam int peopleNum){
		System.out.println("update seat");
		String message = "Update seat of userId=" + userId + " seatId = " + seatId;
		String defaultDes = "no description";
		try {
			int restId = restaurantService.getRestIdByUserId(userId);
			seatService.updateSeat(seatId, restId, seatNo, peopleNum, description);			
			message += " success!";
			return "redirect:getSeatInfosByUserId?userId=" + userId;
		} catch (Exception e) {
			message = message + " error! Reason:" + e.getMessage();
			return "redirect:getSeatInfosByUserId?userId=" + userId;
		}
	}
	
	private List<String> getNextWeek() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		List<String> dateStrings = new ArrayList<String>();		
		for(int i = 0; i < 7; i++){
			date.setDate(date.getDate()+1);
			dateStrings.add(dateFormat.format(date).toString());
		}		
		return dateStrings;
	}
	
}
