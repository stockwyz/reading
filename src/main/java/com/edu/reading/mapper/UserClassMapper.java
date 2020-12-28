package com.edu.reading.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.reading.model.Classes;
import com.edu.reading.model.UserClass;
import com.edu.reading.model.UserClassExample;

public interface UserClassMapper {
    int countByExample(UserClassExample example);

    int deleteByExample(UserClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserClass record);

    int insertSelective(UserClass record);

    List<UserClass> selectByExample(UserClassExample example);

    UserClass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserClass record, @Param("example") UserClassExample example);

    int updateByExample(@Param("record") UserClass record, @Param("example") UserClassExample example);

    int updateByPrimaryKeySelective(UserClass record);

    int updateByPrimaryKey(UserClass record);
}