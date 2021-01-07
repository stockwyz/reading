package com.edu.reading.mapper;

import com.edu.reading.model.Lesson;
import com.edu.reading.model.LessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LessonMapper {
    int countByExample(LessonExample example);

    int deleteByExample(LessonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    List<Lesson> selectByExampleWithBLOBs(LessonExample example);

    List<Lesson> selectByExample(LessonExample example);

    Lesson selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Lesson record, @Param("example") LessonExample example);

    int updateByExampleWithBLOBs(@Param("record") Lesson record, @Param("example") LessonExample example);

    int updateByExample(@Param("record") Lesson record, @Param("example") LessonExample example);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKeyWithBLOBs(Lesson record);

    int updateByPrimaryKey(Lesson record);
}