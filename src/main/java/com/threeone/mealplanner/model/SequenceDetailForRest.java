package com.threeone.mealplanner.model;

import com.threeone.mealplanner.model.entity.SequenceInfo;
import com.threeone.mealplanner.model.entity.UserInfo;

/**
 * ĳ���������Ŷ���Ϣ
 * 
 * @author wangyongbo
 * 
 */
public class SequenceDetailForRest {
	private SequenceInfo sequenceInfo;
	private UserInfo userInfo;
	
	public SequenceInfo getSequenceInfo() {
		return sequenceInfo;
	}
	public void setSequenceInfo(SequenceInfo sequenceInfo) {
		this.sequenceInfo = sequenceInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
