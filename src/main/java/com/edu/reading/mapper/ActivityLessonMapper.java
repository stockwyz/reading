package com.edu.reading.mapper;

import com.edu.reading.model.ActivityLesson;
import com.edu.reading.model.ActivityLessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityLessonMapper {
    int countByExample(ActivityLessonExample example);

    int deleteByExample(ActivityLessonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityLesson record);

    int insertSelective(ActivityLesson record);

    List<ActivityLesson> selectByExample(ActivityLessonExample example);

    ActivityLesson selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityLesson record, @Param("example") ActivityLessonExample example);

    int updateByExample(@Param("record") ActivityLesson record, @Param("example") ActivityLessonExample example);

    int updateByPrimaryKeySelective(ActivityLesson record);

    int updateByPrimaryKey(ActivityLesson record);
}