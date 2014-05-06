package com.threeone.mealplanner.model;

import java.util.List;

import com.threeone.mealplanner.model.entity.MealInfo;

/**
 * 饭局以及相应的好友回复状态等
 * @author fengxiangmin
 *
 */
public class MealWithFriends {
	
	private MealInfo mealInfo;
	private List<MealFriendWithStatus> mealFriendWithStatusList;
	
	public MealInfo getMealInfo() {
		return mealInfo;
	}
	public void setMealInfo(MealInfo mealInfo) {
		this.mealInfo = mealInfo;
	}
	public List<MealFriendWithStatus> getMealFriendWithStatusList() {
		return mealFriendWithStatusList;
	}
	public void setMealFriendWithStatusList(
			List<MealFriendWithStatus> mealFriendWithStatusList) {
		this.mealFriendWithStatusList = mealFriendWithStatusList;
	}

}
