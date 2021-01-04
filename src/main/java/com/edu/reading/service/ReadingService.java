package com.edu.reading.service;

import java.util.List;
import java.util.Map;

import com.edu.reading.dto.SubjectQueryDto;
import com.edu.reading.model.Book;
import com.edu.reading.model.User;

public interface ReadingService {
	Map<String, Object> mine(String openId);
	
	Integer updatePersonInfo(User user);
	
	Map<String, Object> home(String openId);
	
	List<Book> querySubject( SubjectQueryDto subjectDto);
}
