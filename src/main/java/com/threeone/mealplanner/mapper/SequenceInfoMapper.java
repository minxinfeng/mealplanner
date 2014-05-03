package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.SequenceInfo;

public interface SequenceInfoMapper {
    int deleteByPrimaryKey(Integer seqid);

    int insert(SequenceInfo record);

    int insertSelective(SequenceInfo record);

    SequenceInfo selectByPrimaryKey(Integer seqid);

    int updateByPrimaryKeySelective(SequenceInfo record);

    int updateByPrimaryKey(SequenceInfo record);
}