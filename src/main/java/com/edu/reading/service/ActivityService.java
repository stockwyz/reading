package com.edu.reading.service;

import java.util.List;

import com.edu.reading.dto.ActivityQueryDto;
import com.edu.reading.model.Activity;

public interface ActivityService {
	List<Activity> getAll();
	
	List<Activity> mine(ActivityQueryDto dto);
}
