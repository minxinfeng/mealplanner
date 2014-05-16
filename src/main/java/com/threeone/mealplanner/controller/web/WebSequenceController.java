package com.threeone.mealplanner.controller.web;

import java.util.List;

import javax.mail.Flags.Flag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.common.Message;
import com.threeone.mealplanner.model.SequenceDetailForRest;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.service.SequenceService;

@Controller
@RequestMapping("/web/sequence")
public class WebSequenceController {

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private SequenceService sequenceService;
	
	@RequestMapping(value = "getSequenceByUserId", method = RequestMethod.GET)
	public String getSequenceByUserId(@RequestParam int userId, Model model){
		String message = "Get sequence for userId = " + userId;
		try {
			System.out.println("sequence start");
			int restId = restaurantService.getRestIdByUserId(userId);
			List<SequenceDetailForRest> sequenceDetailForRests = sequenceService.getAllSeqInfosByRest(restId);
			model.addAttribute("sequenceDetailForRests", sequenceDetailForRests);
			message = message + "success";
			System.out.println("success");
			return "sequence/sequence.ftl";
		} catch (InternalException e) {
			message = message + "Failed. Reason : " + e.getMessage();
			return "sequence/sequence.ftl";
		}
	}
	
	@RequestMapping(value = "acceptSquenceBySeqId", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> acceptSquenceBySeqId(@RequestParam int seqId){
		Boolean flag = false;
		String message = "Accept sequence for seqId = " + seqId;
		try {
			sequenceService.changeToEating(seqId);
			message = message + "success";
			flag = true;
			return new JsonResult<String>(flag, message);
		} catch (InternalException e) {
			message = message + "Failed. Reason : " + e.getMessage();
			return new JsonResult<String>(flag, message);
		}
	}
	
	@RequestMapping(value = "cancleSquenceBySeqId", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<String> cancleSquenceBySeqId(@RequestParam int seqId){
		Boolean flag = false;
		String message = "Cancle sequence for seqId = " + seqId;
		try {
			sequenceService.cancleSeq(seqId);
			message = message + "success";
			flag = true;
			return new JsonResult<String>(flag, message);
		} catch (InternalException e) {
			message = message + "Failed. Reason : " + e.getMessage();
			return new JsonResult<String>(flag, message);
		}
	}
}
