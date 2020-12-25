package com.edu.reading.mapper;

import com.edu.reading.model.UserBookAudio;
import com.edu.reading.model.UserBookAudioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBookAudioMapper {
    int countByExample(UserBookAudioExample example);

    int deleteByExample(UserBookAudioExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBookAudio record);

    int insertSelective(UserBookAudio record);

    List<UserBookAudio> selectByExample(UserBookAudioExample example);

    UserBookAudio selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBookAudio record, @Param("example") UserBookAudioExample example);

    int updateByExample(@Param("record") UserBookAudio record, @Param("example") UserBookAudioExample example);

    int updateByPrimaryKeySelective(UserBookAudio record);

    int updateByPrimaryKey(UserBookAudio record);
}