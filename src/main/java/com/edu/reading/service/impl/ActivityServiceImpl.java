package com.edu.reading.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.edu.reading.dto.ActivityQueryDto;
import com.edu.reading.mapper.ActivityMapper;
import com.edu.reading.mapper.UserMapper;
import com.edu.reading.model.Activity;
import com.edu.reading.model.ActivityExample;
import com.edu.reading.model.User;
import com.edu.reading.model.UserExample;
import com.edu.reading.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<Activity> getAll() {
		ActivityExample ex = new ActivityExample();
		ex.setOrderByClause("type, end_time desc");
		// 已经发布未结束
		ex.createCriteria().andPublishedEqualTo(1).andFinishedEqualTo(0);
		return activityMapper.selectByExample(ex);
	}

	@Override
	public List<Activity> mine(ActivityQueryDto dto) {
		// TODO Auto-generated method stub
		List<Activity> result = null;
		UserExample ue = new UserExample();
		ue.createCriteria().andOpenidEqualTo(dto.getOpenid());
		List<User> lst = userMapper.selectByExample(ue);
		if(!ObjectUtils.isEmpty(lst)) {
			User user = lst.get(0);
			dto.setUserId(user.getId());
			result = activityMapper.getActivityByUserId(dto);
		}
		return result;
	}

}
