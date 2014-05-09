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
	
	List<SequenceDetailForRest> getAllSeqInfosByRest(int restId);
	
}
