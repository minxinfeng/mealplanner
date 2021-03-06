package com.threeone.mealplanner.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.mapper.RestaurantInfoMapper;
import com.threeone.mealplanner.mapper.SequenceInfoMapper;
import com.threeone.mealplanner.mapper.UserInfoMapper;
import com.threeone.mealplanner.model.SequenceDetailForRest;
import com.threeone.mealplanner.model.SequenceDetailForUser;
import com.threeone.mealplanner.model.SequenceStatus;
import com.threeone.mealplanner.model.entity.SeatInfo;
import com.threeone.mealplanner.model.entity.SequenceInfo;
import com.threeone.mealplanner.model.entity.UserInfo;
import com.threeone.mealplanner.push.PushService;
import com.threeone.mealplanner.service.SeatService;
import com.threeone.mealplanner.service.SequenceService;

public class SequenceServiceImpl implements SequenceService {

	private static final Log LOG = LogFactory.getLog(SequenceServiceImpl.class); 
	private SequenceInfoMapper sequenceInfoMapper;
	private UserInfoMapper userInfoMapper;
	private RestaurantInfoMapper restaurantInfoMapper;
	
	@Autowired
	private SeatService seatService;
	
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
			int restId = sequenceInfo.getRestid();
			int userId = sequenceInfo.getUserid();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String dateString = formatter.format(now);
			String dateDay = dateString.split(" ")[0];
			int dateClock = now.getHours();
			
			int seatType = this.getSeatType(sequenceInfo.getPeoplenum());
			
			int inSeqNum = sequenceInfoMapper.getSeqInListNum(restId, seatType, dateDay);
			
			List<SeatInfo> seatInfos = seatService.getAvailableSeats(restId, dateDay, dateClock, sequenceInfo.getPeoplenum());
			//如果有空座且排队队列为空时，不进入排队程序
			if(!seatInfos.isEmpty() && inSeqNum == 0){
				LOG.error("userId=" + userId + " line up cancle due to there has free seat!");
				throw new InternalException("Free");
			}else {
				//无空座，进入排队程序
				SequenceDetailForUser sequenceDetailForUser = this.getSequenceInfo(userId);
				//已在队列中，提示不可排队
				if(sequenceDetailForUser != null){
					LOG.error("userId=" + userId + " has in sequence, line up cancle!");
					throw new InternalException("hasLineUp");
				}else{
					//不在队列中，进行排队
					sequenceDetailForUser = new SequenceDetailForUser();
					sequenceDetailForUser.setPeopleNum(sequenceInfo.getPeoplenum());
					sequenceDetailForUser.setRestId(sequenceInfo.getRestid());
					sequenceDetailForUser.setUserId(sequenceInfo.getUserid());
					
					//1.生成对应的seqNo
					int seqNo = this.getSeqNo(restId);
					sequenceDetailForUser.setSeqNo(seqNo);
					//2.获取排在当前队列未用餐的第一个人的排队号
					int seqNow = this.getSeqNow(restId);
					sequenceDetailForUser.setSeqNow(seqNow);
					//3. 获取当前队列排队的队数
					int peopleBefore = this.getSeqBefore(restId, seatType);
					sequenceDetailForUser.setPeopleBefore(peopleBefore);
					sequenceDetailForUser.setSeatType(seatType);
					//插入记录
					sequenceInfo.setSeattype(seatType);
					sequenceInfo.setSeqdate(new Date());
					sequenceInfo.setSeqno(seqNo);
					sequenceInfo.setStatus(SequenceStatus.waiting.getValue());
					sequenceInfoMapper.insertSelective(sequenceInfo);
					sequenceDetailForUser.setSeqId(sequenceInfoMapper.getLatestSeqId(restId, seatType));
					LOG.info("insert seq success!");
					return sequenceDetailForUser;
				}
			}
			
		} catch (Exception e) {
			String message = "insert seq failed.Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	

	public void cancleSeq(int userId) throws InternalException {
		try {
			SequenceInfo sequenceInfo = sequenceInfoMapper.getLatestSeqByUserId(userId,getDateDay());
			int seqId = sequenceInfo.getSeqid();
			sequenceInfoMapper.updateSeqStatus(seqId, SequenceStatus.cancle.getValue());
			LOG.info("Cancle  seq of userId=" + seqId + " success!");
		} catch (Exception e) {
			String message = "Cancle seq of userId=" + userId + " failed!Reason:" + e.getMessage();
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
				//向刚刚进入用餐状态的用户发送通知，出队
				int userId = sequenceInfo.getUserid();
				pushService.setUserId(userId);
				pushService.setTitle("用餐时刻");
				pushService.setDescription("亲，等了这么久，终于可以吃上可口的饭菜啦，还等神马!");
				Thread thread = new Thread(pushService);
				thread.run();
				
				//向后面排队的人发送消息
				SequenceInfo sequenceInfoForPush = this.getPushUserId(sequenceInfo);
				if(sequenceInfoForPush != null){
					int seatType = sequenceInfoForPush.getSeattype();
					pushService.setUserId(sequenceInfoForPush.getUserid());
					pushService.setTitle("用餐时间正在靠近");
					pushService.setDescription("亲，您有" + sequenceInfoForPush.getPeoplenum() + "人就餐，为您提供了" + seatType + "人桌，前面还有2位排队" + seatType + "人桌，请尽快回到餐厅，以防错过排号。");
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
	
	public SequenceDetailForUser getSequenceInfo(int userId) throws InternalException{
		try {
			
			SequenceDetailForUser sequenceDetailForUser = new SequenceDetailForUser();
			SequenceInfo sequenceInfo = new SequenceInfo();
			sequenceInfo = sequenceInfoMapper.getLatestSeqByUserId(userId,getDateDay());
			if(sequenceInfo != null){
				sequenceDetailForUser = toSequenceDetailForUser(sequenceInfo);
			}else {
				sequenceDetailForUser = null;
			}
			LOG.info("Get seq of userId=" + userId + " detail info success!");
			return sequenceDetailForUser;
		} catch (Exception e) {
			String message = "Get seq of userId=" + userId + " detail info failed!Reason:" + e.getMessage();
			LOG.error(message);
			throw new InternalException(message);
		}
	}
	
	//通过排队信息获取排队详细信息，包括排队的详细信息
	private SequenceDetailForUser toSequenceDetailForUser(SequenceInfo sequenceInfo){
		SequenceDetailForUser sequenceDetailForUser = new SequenceDetailForUser();
		int seqId = sequenceInfo.getSeqid();
		int restId = sequenceInfo.getRestid();
		int seatType = sequenceInfo.getSeattype();
		sequenceDetailForUser.setPeopleNum(sequenceInfo.getPeoplenum());
		sequenceDetailForUser.setRestId(restId);
		sequenceDetailForUser.setUserId(sequenceInfo.getUserid());
		sequenceDetailForUser.setPeopleBefore(sequenceInfoMapper.getSeqBeforeSeqId(seqId, restId, seatType, getDateDay()));
		sequenceDetailForUser.setSeatType(seatType);
		sequenceDetailForUser.setSeqId(seqId);
		sequenceDetailForUser.setSeqNo(sequenceInfo.getSeqno());
		sequenceDetailForUser.setSeqNow(this.getSeqNow(restId));
		sequenceDetailForUser.setRestName(restaurantInfoMapper.selectByPrimaryKey(restId).getRestname());
		return sequenceDetailForUser;
	}
	
	//获取今天的日期
	private String getDateDay(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		String dateDay = dateString.split(" ")[0];
		return dateDay;
	}
	
	//获取在某个餐厅的特定排队号
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
	
	//获取正在用餐的排队号
	private int getSeqNow(int restId){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		String dateDay = dateString.split(" ")[0];
		Integer seqNow = sequenceInfoMapper.getSeqNow(restId, dateDay);
		if(seqNow == null ){
			seqNow = 0;
		}
		return seqNow;
	}
	//获取排在前面的队数
	private int getSeqBefore(int restId, int seatType){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		String dateDay = dateString.split(" ")[0];
		int seqBefore = sequenceInfoMapper.getSeqBefore(restId, seatType,dateDay);
		return seqBefore;
	}
	//获取是几人桌
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

	//获取排队队列中待提醒的用户信息（当前服务的人相应队列后面的第二组）
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

	//获取用户ID
	public int getUserIdBySeq(int seqId){
		return sequenceInfoMapper.selectByPrimaryKey(seqId).getUserid();
	}

	public void setRestaurantInfoMapper(RestaurantInfoMapper restaurantInfoMapper) {
		this.restaurantInfoMapper = restaurantInfoMapper;
	}

}
