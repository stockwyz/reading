package com.edu.reading.service;

import java.util.Map;

import com.edu.reading.model.User;

public interface ReadingService {
	Map<String, Object> mine(String openId);
	
	Integer updatePersonInfo(User user);
	
	Map<String, Object> home(String openId);
}
