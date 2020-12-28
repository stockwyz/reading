package com.edu.reading.service;

public interface SysService {
	public static final String WX_URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	public static final String WX_URL_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	
	String getAccessToken() throws Exception;
	
	String wechatSession(String code) throws Exception;
	
}
