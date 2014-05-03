package com.threeone.mealplanner.mapper;

import com.threeone.mealplanner.model.entity.Sequenceinfo;

public interface SequenceinfoMapper {
    int deleteByPrimaryKey(Integer seqid);

    int insert(Sequenceinfo record);

    int insertSelective(Sequenceinfo record);

    Sequenceinfo selectByPrimaryKey(Integer seqid);

    int updateByPrimaryKeySelective(Sequenceinfo record);

    int updateByPrimaryKey(Sequenceinfo record);
}