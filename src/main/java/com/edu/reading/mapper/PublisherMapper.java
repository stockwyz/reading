package com.edu.reading.mapper;

import com.edu.reading.model.Publisher;
import com.edu.reading.model.PublisherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PublisherMapper {
    int countByExample(PublisherExample example);

    int deleteByExample(PublisherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Publisher record);

    int insertSelective(Publisher record);

    List<Publisher> selectByExample(PublisherExample example);

    Publisher selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Publisher record, @Param("example") PublisherExample example);

    int updateByExample(@Param("record") Publisher record, @Param("example") PublisherExample example);

    int updateByPrimaryKeySelective(Publisher record);

    int updateByPrimaryKey(Publisher record);
}