package com.threeone.mealplanner.model;

import java.util.List;

import com.threeone.mealplanner.model.entity.SequenceInfo;

/**
 * 某个餐厅的排队信息
 * 
 * @author fengxiangmin
 * 
 */
public class SequenceDetailForRest {
	private int restId;
	private int peopleNum;
	private List<SequenceInfo> sequenceInfos;

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public List<SequenceInfo> getSequenceInfos() {
		return sequenceInfos;
	}

	public void setSequenceInfos(List<SequenceInfo> sequenceInfos) {
		this.sequenceInfos = sequenceInfos;
	}

}
