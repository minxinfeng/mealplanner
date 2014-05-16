package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.SequenceDetailForRest;
import com.threeone.mealplanner.model.SequenceDetailForUser;
import com.threeone.mealplanner.model.entity.SequenceInfo;

/**
 * 排队相关
 * @author fengxiangmin
 *
 */
public interface SequenceService {
	
	//创建排队
	SequenceDetailForUser createSequence(SequenceInfo sequenceInfo) throws InternalException;
	
	//取消排队
	void cancleSeq(int userId) throws InternalException;
	
	//排队转为用餐状态,同时向其后面的第二个排队的人发送消息提醒
	void changeToEating(int seqId) throws InternalException;
	
	List<SequenceDetailForRest> getAllSeqInfosByRest(int restId);
	//获取排队详细信息
	SequenceInfo getSequenceInfo(int userId) throws InternalException;
}
