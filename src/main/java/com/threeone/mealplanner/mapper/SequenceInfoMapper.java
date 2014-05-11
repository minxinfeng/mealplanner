package com.threeone.mealplanner.mapper;

import org.apache.ibatis.annotations.Param;

import com.threeone.mealplanner.model.entity.SequenceInfo;

public interface SequenceInfoMapper {
    int deleteByPrimaryKey(Integer seqid);

    int insert(SequenceInfo record);

    int insertSelective(SequenceInfo record);

    SequenceInfo selectByPrimaryKey(Integer seqid);

    int updateByPrimaryKeySelective(SequenceInfo record);

    int updateByPrimaryKey(SequenceInfo record);
    
    Integer getSeqNo(@Param("restId") int restId, @Param("dateDay") String dateDay);
    
    Integer getSeqNow(@Param("restId") int restId);
    
    int getSeqBefore(@Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
    
    void updateSeqStatus(@Param("seqId") int seqId, @Param("status") int status);	
    //刚刚插入的记录的seqId
    int getLatestSeqId(@Param("restId") int restId, @Param("seatType") int seatType);
}