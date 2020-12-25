package com.edu.reading.mapper;

import com.edu.reading.model.ActivityBook;
import com.edu.reading.model.ActivityBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityBookMapper {
    int countByExample(ActivityBookExample example);

    int deleteByExample(ActivityBookExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityBook record);

    int insertSelective(ActivityBook record);

    List<ActivityBook> selectByExample(ActivityBookExample example);

    ActivityBook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityBook record, @Param("example") ActivityBookExample example);

    int updateByExample(@Param("record") ActivityBook record, @Param("example") ActivityBookExample example);

    int updateByPrimaryKeySelective(ActivityBook record);

    int updateByPrimaryKey(ActivityBook record);
}