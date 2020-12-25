package com.edu.reading.mapper;

import com.edu.reading.model.UserActivityBook;
import com.edu.reading.model.UserActivityBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActivityBookMapper {
    int countByExample(UserActivityBookExample example);

    int deleteByExample(UserActivityBookExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserActivityBook record);

    int insertSelective(UserActivityBook record);

    List<UserActivityBook> selectByExample(UserActivityBookExample example);

    UserActivityBook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserActivityBook record, @Param("example") UserActivityBookExample example);

    int updateByExample(@Param("record") UserActivityBook record, @Param("example") UserActivityBookExample example);

    int updateByPrimaryKeySelective(UserActivityBook record);

    int updateByPrimaryKey(UserActivityBook record);
}