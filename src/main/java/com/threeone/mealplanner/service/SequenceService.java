package com.threeone.mealplanner.service;

import java.util.List;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.model.SequenceDetailForRest;
import com.threeone.mealplanner.model.SequenceDetailForUser;
import com.threeone.mealplanner.model.entity.SequenceInfo;

/**
 * �Ŷ����
 * @author fengxiangmin
 *
 */
public interface SequenceService {
	
	//�����Ŷ�
	SequenceDetailForUser createSequence(SequenceInfo sequenceInfo) throws InternalException;
	
	//ȡ���Ŷ�
	void cancleSeq(int userId) throws InternalException;
	
	//�Ŷ�תΪ�ò�״̬,ͬʱ�������ĵڶ����Ŷӵ��˷�����Ϣ����
	void changeToEating(int seqId) throws InternalException;
	
	List<SequenceDetailForRest> getAllSeqInfosByRest(int restId);
	//��ȡ�Ŷ���ϸ��Ϣ
	SequenceInfo getSequenceInfo(int userId) throws InternalException;
}
