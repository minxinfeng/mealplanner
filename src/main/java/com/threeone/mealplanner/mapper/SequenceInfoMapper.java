package com.threeone.mealplanner.mapper;

import java.util.List;

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
    
    Integer getSeqNow(@Param("restId") int restId, @Param("dateDay") String dateDay);
    //入队时前面排的队数
    int getSeqBefore(@Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
    //某人前面排的队数
    int getSeqBeforeSeqId(@Param("seqId") int seqId, @Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
    
    void updateSeqStatus(@Param("seqId") int seqId, @Param("status") int status);	
    //刚刚插入的记录的seqId
    int getLatestSeqId(@Param("restId") int restId, @Param("seatType") int seatType);
    //根据用户ID获取当前的排队信息
    SequenceInfo getLatestSeqByUserId(@Param("userId") int userId, @Param("dateDay") String dateDay);
    
    List<SequenceInfo> getSequenceInfos(@Param("restId") int restId);
    
    //获取排队队列中需要提醒的用户排队信息
    List<SequenceInfo> getPushSeqInfos(@Param("restId") int restId, @Param("seqId") int seqId, @Param("seatType") int seatType);
    //获取当前排队的队数
    int getSeqInListNum(@Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
}
