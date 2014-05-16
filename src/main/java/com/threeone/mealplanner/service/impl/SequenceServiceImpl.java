package com.threeone.mealplanner.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.SequenceInfoMapper;
import com.threeone.mealplanner.mapper.UserInfoMapper;
import com.threeone.mealplanner.model.SequenceDetailForRest;
import com.threeone.mealplanner.model.SequenceDetailForUser;
import com.threeone.mealplanner.model.SequenceStatus;
import com.threeone.mealplanner.model.entity.SequenceInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.push.PushService;
import com.threeone.mealplanner.service.SequenceService;

public class SequenceServiceImpl implements SequenceService {

	private static final Log LOG = LogFactory.getLog(SequenceServiceImpl.class); 
	private SequenceInfoMapper sequenceInfoMapper;
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private PushService pushService;
	
	
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}


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
			int seatType = this.getSeatType(sequenceInfo.getPeoplenum());
			int peopleBefore = this.getSeqBefore(restId, seatType);
			sequenceDetailForUser.setPeopleBefore(peopleBefore);
			sequenceDetailForUser.setSeatType(seatType);
			//�����¼
			sequenceInfo.setSeattype(seatType);
			sequenceInfo.setSeqdate(new Date());
			sequenceInfo.setSeqno(seqNo);
			sequenceInfo.setStatus(SequenceStatus.waiting.getValue());
			sequenceInfoMapper.insertSelective(sequenceInfo);
			sequenceDetailForUser.setSeqId(sequenceInfoMapper.getLatestSeqId(restId, seatType));
			LOG.info("insert seq success!");
			return sequenceDetailForUser;
		} catch (Exception e) {
			String message = "insert seq failed.Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
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
			try {
				SequenceInfo sequenceInfo = sequenceInfoMapper.selectByPrimaryKey(seqId);
				//��ոս����ò�״̬���û�����֪ͨ������
				int userId = sequenceInfo.getUserid();
				pushService.setUserId(userId);
				pushService.setTitle("�ò�ʱ��");
				pushService.setDescription("�ף�������ô�ã����ڿ��Գ��Ͽɿڵķ���������������!");
				Thread thread = new Thread(pushService);
				thread.run();
				
				//������Ŷӵ��˷�����Ϣ
				SequenceInfo sequenceInfoForPush = this.getPushUserId(sequenceInfo);
				if(sequenceInfoForPush != null){
					int seatType = sequenceInfoForPush.getSeattype();
					pushService.setUserId(sequenceInfoForPush.getUserid());
					pushService.setTitle("�ò�ʱ�����ڿ���");
					pushService.setDescription("�ף�����" + sequenceInfoForPush.getPeoplenum() + "�˾Ͳͣ�Ϊ���ṩ��" + seatType + "������ǰ�滹��2λ�Ŷ�" + seatType + "�������뾡��ص��������Է�����ź�");
					thread = new Thread(pushService);
					thread.run();
				}
			} catch (Exception e) {
				LOG.error("Error to push notification, Reason:" + e.getMessage());
			}
			
		} catch (Exception e) {
			String message = "Change to eating seqId=" + seqId + " failed!Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	
	public SequenceInfo getSequenceInfo(int seqId) throws InternalException{
		try {
			SequenceInfo sequenceInfo = sequenceInfoMapper.selectByPrimaryKey(seqId);
			LOG.info("Get seqId=" + seqId + " detail info success!");
			return sequenceInfo;
		} catch (Exception e) {
			String message = "Get seqId=" + seqId + " detail info failed!Reason:" + e.getMessage();
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
	private int getSeqBefore(int restId, int seatType){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		String dateDay = dateString.split(" ")[0];
		int seqBefore = sequenceInfoMapper.getSeqBefore(restId, seatType,dateDay);
		return seqBefore;
	}
	//��ȡ�Ǽ�����
	private int getSeatType(int peopleNum){
		int seatType = 2;
		if(peopleNum <= 10){
			if (peopleNum % 2 == 0) {
				seatType = peopleNum;
			}else {
				seatType = peopleNum + 1;
			}
		}else {
			seatType = peopleNum;
		}
		
		return seatType;
	}
	
	public List<SequenceDetailForRest> getAllSeqInfosByRest(int restId) {
		List<SequenceDetailForRest> sequenceDetailForRests = new ArrayList<SequenceDetailForRest>();
		try {
			List<SequenceInfo> sequenceInfos = sequenceInfoMapper.getSequenceInfos(restId);
			for(int i = 0; i < sequenceInfos.size(); i++){
				SequenceInfo sequenceInfo = sequenceInfos.get(i);
				UserInfo userInfo = userInfoMapper.getUserInfoById(sequenceInfo.getUserid());
				SequenceDetailForRest sequenceDetailForRest = new SequenceDetailForRest();
				sequenceDetailForRest.setSequenceInfo(sequenceInfo);
				sequenceDetailForRest.setUserInfo(userInfo);
				sequenceDetailForRests.add(sequenceDetailForRest);
			}
			LOG.info("Get sequenceDetailForRests for restId =" + restId + " success!");
		} catch (Exception e) {
			LOG.info("Get sequenceDetailForRests for restId =" + restId + " failed! Reason:" + e.getMessage());
		}
		return sequenceDetailForRests;
	}

	//��ȡ�ŶӶ����д����ѵ��û���Ϣ����ǰ���������Ӧ���к���ĵڶ��飩
	private SequenceInfo getPushUserId(SequenceInfo sequenceInfo){
		SequenceInfo sequenceInfoForPush = null;
		int seqId = sequenceInfo.getSeqid();
		int restId = sequenceInfo.getRestid();
		int seatType = sequenceInfo.getSeattype();
		List<SequenceInfo> sequenceInfos = sequenceInfoMapper.getPushSeqInfos(restId, seqId, seatType);
		if(sequenceInfos != null){
			sequenceInfoForPush = sequenceInfos.get(sequenceInfos.size()-1);
		}
		return sequenceInfoForPush;
	}

}
