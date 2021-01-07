package com.edu.reading.mapper;

import com.edu.reading.model.UserLessonAudio;
import com.edu.reading.model.UserLessonAudioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLessonAudioMapper {
    int countByExample(UserLessonAudioExample example);

    int deleteByExample(UserLessonAudioExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLessonAudio record);

    int insertSelective(UserLessonAudio record);

    List<UserLessonAudio> selectByExample(UserLessonAudioExample example);

    UserLessonAudio selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLessonAudio record, @Param("example") UserLessonAudioExample example);

    int updateByExample(@Param("record") UserLessonAudio record, @Param("example") UserLessonAudioExample example);

    int updateByPrimaryKeySelective(UserLessonAudio record);

    int updateByPrimaryKey(UserLessonAudio record);
}