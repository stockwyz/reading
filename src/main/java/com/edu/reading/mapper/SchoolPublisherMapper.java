package com.edu.reading.mapper;

import com.edu.reading.model.SchoolPublisher;
import com.edu.reading.model.SchoolPublisherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolPublisherMapper {
    int countByExample(SchoolPublisherExample example);

    int deleteByExample(SchoolPublisherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SchoolPublisher record);

    int insertSelective(SchoolPublisher record);

    List<SchoolPublisher> selectByExample(SchoolPublisherExample example);

    SchoolPublisher selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SchoolPublisher record, @Param("example") SchoolPublisherExample example);

    int updateByExample(@Param("record") SchoolPublisher record, @Param("example") SchoolPublisherExample example);

    int updateByPrimaryKeySelective(SchoolPublisher record);

    int updateByPrimaryKey(SchoolPublisher record);
}