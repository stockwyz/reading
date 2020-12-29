package com.edu.reading.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.edu.reading.mapper.ClassesMapper;
import com.edu.reading.mapper.UserMapper;
import com.edu.reading.model.User;
import com.edu.reading.model.UserExample;
import com.edu.reading.service.ReadingService;

@Service
public class ReadingServiceImpl implements ReadingService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ClassesMapper classesMapper;
	
	@Override
	public Map<String, Object> mine(String openId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<>();
		UserExample ue = new UserExample();
		ue.createCriteria().andOpenidEqualTo(openId);
		List<User> lst = userMapper.selectByExample(ue);
		if(!ObjectUtils.isEmpty(lst)) {
			User user = lst.get(0);
			result.put("user", user);
			result.put("classList", classesMapper.getClassByUserId(user.getId()));
		}
		return result;
	}

	@Override
	public Map<String, Object> home(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updatePersonInfo(User user) {
		UserExample ue = new UserExample();
		if(user.getId() == null) {
			ue.createCriteria().andOpenidEqualTo(user.getOpenid());
			List<User> lst = userMapper.selectByExample(ue);
			if(!ObjectUtils.isEmpty(lst)) {
				user.setUpdateUser(lst.get(0).getId());
			}			
		} else {
			user.setUpdateUser(user.getId());
		}

		user.setUpdateDate(new Date());
		UserExample ex = new UserExample();
		ex.createCriteria().andOpenidEqualTo(user.getOpenid());
		return userMapper.updateByExampleSelective(user, ex);
	}
}
