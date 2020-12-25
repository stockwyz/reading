package com.edu.reading.controller;

import java.util.Map;


public class BaseController {
	public final static ThreadLocal<Map<String, String>> UserLocal = new ThreadLocal<>();
	public String getUserId() {
		return UserLocal.get().get("userId");
	}
	public String getUserName() {
		return UserLocal.get().get("userName");
	}
}
