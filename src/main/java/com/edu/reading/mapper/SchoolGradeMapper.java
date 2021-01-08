package com.edu.reading.mapper;

import com.edu.reading.model.SchoolGrade;
import com.edu.reading.model.SchoolGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolGradeMapper {
    int countByExample(SchoolGradeExample example);

    int deleteByExample(SchoolGradeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SchoolGrade record);

    int insertSelective(SchoolGrade record);

    List<SchoolGrade> selectByExample(SchoolGradeExample example);

    SchoolGrade selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SchoolGrade record, @Param("example") SchoolGradeExample example);

    int updateByExample(@Param("record") SchoolGrade record, @Param("example") SchoolGradeExample example);

    int updateByPrimaryKeySelective(SchoolGrade record);

    int updateByPrimaryKey(SchoolGrade record);
}