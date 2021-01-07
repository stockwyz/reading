package com.edu.reading.mapper;

import com.edu.reading.model.UserLesson;
import com.edu.reading.model.UserLessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLessonMapper {
    int countByExample(UserLessonExample example);

    int deleteByExample(UserLessonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLesson record);

    int insertSelective(UserLesson record);

    List<UserLesson> selectByExample(UserLessonExample example);

    UserLesson selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLesson record, @Param("example") UserLessonExample example);

    int updateByExample(@Param("record") UserLesson record, @Param("example") UserLessonExample example);

    int updateByPrimaryKeySelective(UserLesson record);

    int updateByPrimaryKey(UserLesson record);
}