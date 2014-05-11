package com.threeone.mealplanner.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.model.SequenceDetailForUser;
import com.threeone.mealplanner.model.entity.SequenceInfo;
import com.threeone.mealplanner.service.SequenceService;

@Controller
@RequestMapping("/app/seq")
public class AppSequenceController {
	
	@Autowired
	private SequenceService sequenceService;
	
	@RequestMapping("/insertSeq")
	@ResponseBody
	public JsonResult<SequenceDetailForUser> insertSeq(@RequestParam int userId, @RequestParam int restId, @RequestParam int peopleNum){
		String message = "insert seq ";
		Boolean flag = false;
		SequenceDetailForUser sequenceDetailForUser = null;
		try {
			SequenceInfo sequenceInfo = new SequenceInfo();
			sequenceInfo.setRestid(restId);
			sequenceInfo.setPeoplenum(peopleNum);
			sequenceInfo.setUserid(userId);
			sequenceDetailForUser = sequenceService.createSequence(sequenceInfo);
			flag = true;
			message = message + "success!";
		} catch (Exception e) {
			message = message + "failed. Reason:" +e.getMessage();
			
		}
		
		return new JsonResult<SequenceDetailForUser>(flag, message, sequenceDetailForUser);
	}
	
	@RequestMapping("/cancle")
	@ResponseBody
	public JsonResult<String> cancle(@RequestParam int userId, @RequestParam int seqId){
		String message = "cancle seqId = " + seqId + " ";
		Boolean flag = false;
		try {
			sequenceService.cancleSeq(seqId);
			flag = true;
			message = message + "success!";
		} catch (Exception e) {
			message = message + "failed. Reason:" +e.getMessage();
			
		}
		
		return new JsonResult<String>(flag, message, null);
	}
}
