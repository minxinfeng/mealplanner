package com.threeone.mealplanner.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.SequenceInfoMapper;
import com.threeone.mealplanner.model.SequenceDetailForRest;
import com.threeone.mealplanner.model.SequenceDetailForUser;
import com.threeone.mealplanner.model.SequenceStatus;
import com.threeone.mealplanner.model.entity.SequenceInfo;
import com.threeone.mealplanner.service.SequenceService;

public class SequenceServiceImpl implements SequenceService {

	private static final Log LOG = LogFactory.getLog(SequenceServiceImpl.class); 
	private SequenceInfoMapper sequenceInfoMapper;
	
	public void setSequenceInfoMapper(SequenceInfoMapper sequenceInfoMapper) {
		this.sequenceInfoMapper = sequenceInfoMapper;
	}
	
	
	public SequenceDetailForUser createSequence(SequenceInfo sequenceInfo) throws InternalException{
		try {
			SequenceDetailForUser sequenceDetailForUser = new SequenceDetailForUser();
			sequenceDetailForUser.setPeopleNum(sequenceInfo.getPeoplenum());
			sequenceDetailForUser.setRestId(sequenceInfo.getRestid());
			sequenceDetailForUser.setUserId(sequenceInfo.getUserid());
			int restId = sequenceInfo.getRestid();
			//1.���ɶ�Ӧ��seqNo
			int seqNo = this.getSeqNo(restId);
			sequenceDetailForUser.setSeqNo(seqNo);
			//2.��ȡ���ڵ�ǰ����δ�ò͵ĵ�һ���˵��ŶӺ�
			int seqNow = this.getSeqNow(restId);
			sequenceDetailForUser.setSeqNow(seqNow);
			//3. ��ȡ��ǰ�����ŶӵĶ���
			int peopleBefore = this.getSeqBefore(restId, sequenceInfo.getPeoplenum());
			sequenceDetailForUser.setPeopleBefore(peopleBefore);
			//�����¼
			sequenceInfo.setSeqdate(new Date());
			sequenceInfo.setSeqno(seqNo);
			sequenceInfo.setStatus(SequenceStatus.waiting.getValue());
			sequenceInfoMapper.insertSelective(sequenceInfo);
			LOG.info("insert seq success!");
			return sequenceDetailForUser;
		} catch (Exception e) {
			String message = "insert seq failed.Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	
	//��ȡ��ĳ���������ض��ŶӺ�
	private int getSeqNo(int restId){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		String dateDay = dateString.split(" ")[0];
		Integer seqNo = sequenceInfoMapper.getSeqNo(restId, dateDay);
		if(seqNo == null){
			seqNo = 1;
		}
		else if(seqNo == 99){
			seqNo = 1;
		}else {
			seqNo += 1;
		}
		return seqNo;
	}
	
	//��ȡ�����ò͵��ŶӺ�
	private int getSeqNow(int restId){
		Integer seqNow = sequenceInfoMapper.getSeqNow(restId);
		if(seqNow == null ){
			seqNow = 0;
		}
		return seqNow;
	}
	//��ȡ����ǰ��Ķ���
	private int getSeqBefore(int restId, int peopleNum){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		String dateDay = dateString.split(" ")[0];
		int seqBefore = sequenceInfoMapper.getSeqBefore(restId, peopleNum,dateDay);
		return seqBefore;
	}
	
	
	public List<SequenceDetailForRest> getAllSeqInfosByRest(int restId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void cancleSeq(int seqId) throws InternalException {
		try {
			sequenceInfoMapper.updateSeqStatus(seqId, SequenceStatus.cancle.getValue());
			LOG.info("Cancle seqId=" + seqId + " success!");
		} catch (Exception e) {
			String message = "Cancle seqId=" + seqId + " failed!Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

	
	public void changeToEating(int seqId) throws InternalException {
		try {
			sequenceInfoMapper.updateSeqStatus(seqId, SequenceStatus.eating.getValue());
			LOG.info("Change to eating seqId=" + seqId + " success!");
		} catch (Exception e) {
			String message = "Change to eating seqId=" + seqId + " failed!Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}

}
