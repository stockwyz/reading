package com.edu.reading.service;

import java.util.List;

import com.edu.reading.model.Directory;

public interface DirectoryService {
	List<Directory> initTreeList();
	
	boolean reload();
}
