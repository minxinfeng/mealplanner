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
    //���ʱǰ���ŵĶ���
    int getSeqBefore(@Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
    //ĳ��ǰ���ŵĶ���
    int getSeqBeforeSeqId(@Param("seqId") int seqId, @Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
    
    void updateSeqStatus(@Param("seqId") int seqId, @Param("status") int status);	
    //�ող���ļ�¼��seqId
    int getLatestSeqId(@Param("restId") int restId, @Param("seatType") int seatType);
    //�����û�ID��ȡ��ǰ���Ŷ���Ϣ
    SequenceInfo getLatestSeqByUserId(@Param("userId") int userId, @Param("dateDay") String dateDay);
    
    List<SequenceInfo> getSequenceInfos(@Param("restId") int restId);
    
    //��ȡ�ŶӶ�������Ҫ���ѵ��û��Ŷ���Ϣ
    List<SequenceInfo> getPushSeqInfos(@Param("restId") int restId, @Param("seqId") int seqId, @Param("seatType") int seatType);
    //��ȡ��ǰ�ŶӵĶ���
    int getSeqInListNum(@Param("restId") int restId, @Param("seatType") int seatType, @Param("dateDay") String dateDay);
}
