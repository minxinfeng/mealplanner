package com.threeone.mealplanner.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threeone.mealplanner.common.JsonResult;
import com.threeone.mealplanner.mapper.FriendRequestStatus;
import com.threeone.mealplanner.model.UserInfoWithFriends;
import com.threeone.mealplanner.service.FriendService;

@Controller
@RequestMapping("/app/friend")
public class AppFriendController {
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping("/getAllFriends")
	@ResponseBody
	public JsonResult<UserInfoWithFriends> getAllFriends(@RequestParam int userId){
		Boolean flag = true;
		String message = "Get friends info of userId=" + userId;
		try {
			UserInfoWithFriends userInfoWithFriends = friendService.getAllFriendInfos(userId);
			message += " success!";
			return new JsonResult<UserInfoWithFriends>(flag, message, userInfoWithFriends);
		} catch (Exception e) {
			flag = false;
			message = message + " Error. Reason:" + e.getMessage();
			return new JsonResult<UserInfoWithFriends>(flag, message, null);
		}
	}
	
	@RequestMapping("/getFriendsRequest")
	@ResponseBody
	public JsonResult<UserInfoWithFriends> getFriendsRequest(@RequestParam int userId){
		Boolean flag = true;
		String message = "Get friends requesting info of userId=" + userId;
		try {
			UserInfoWithFriends userInfoWithFriends = friendService.getFriendRequestsByStatus(userId, FriendRequestStatus.requesting.getValue());
			message += " success!";
			return new JsonResult<UserInfoWithFriends>(flag, message, userInfoWithFriends);
		} catch (Exception e) {
			flag = false;
			message = message + " Error. Reason:" + e.getMessage();
			return new JsonResult<UserInfoWithFriends>(flag, message, null);
		}
	}
	
	@RequestMapping("/addFriendsRequest")
	@ResponseBody
	public JsonResult<String> addFriendsRequest(@RequestParam int userId, @RequestParam int friendId){
		Boolean flag = true;
		String message = "userId=" + userId + " Send friends requesting to friendId=" + friendId;
		try {
			friendService.addFriendRequest(userId, friendId);
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			flag = false;
			message = message + " Error. Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping("/acceptFriendsRequest")
	@ResponseBody
	public JsonResult<String> acceptFriendsRequest(@RequestParam int userId, @RequestParam int friendId){
		Boolean flag = true;
		String message = "userId=" + userId + " receive friends requesting to friendId=" + friendId;
		try {
			friendService.handleFriendRequest(userId, friendId, FriendRequestStatus.accept.getValue());
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + " Error. Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}
	
	@RequestMapping("/rejectFriendsRequest")
	@ResponseBody
	public JsonResult<String> rejectFriendsRequest(@RequestParam int userId, @RequestParam int friendId){
		Boolean flag = true;
		String message = "userId=" + userId + " reject friends requesting to friendId=" + friendId;
		try {
			friendService.handleFriendRequest(userId, friendId, FriendRequestStatus.reject.getValue());
			message += " success!";
			return new JsonResult<String>(flag, message, null);
		} catch (Exception e) {
			message = message + " Error. Reason:" + e.getMessage();
			return new JsonResult<String>(flag, message, null);
		}
	}

}
