package com.edu.reading.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.edu.reading.mapper.UserMapper;
import com.edu.reading.model.User;
import com.edu.reading.model.UserExample;
import com.edu.reading.service.SysService;
import com.edu.reading.util.HttpUtil;

@Service
public class SysServiceImpl implements SysService {
	
	@Value("${wechat.AppID}")
	private String appid;
	
	@Value("${wechat.AppSecret}")
	private String appSecret;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String getAccessToken() throws Exception {
		String token = null;
		Map<String, Object> result = HttpUtil.doGet(String.format(WX_URL_TOKEN, appid, appSecret));
		if((Integer) result.get("code") == 200) {
			JSONObject rspObj = JSONObject.parseObject((String) result.get("response"));
			if(rspObj.getInteger("errcode") ==0) {
				token = rspObj.getString("access_token");
			} else {
				String err = "ErrCode:" + rspObj.getInteger("errcode") + " ErrMsg:" + rspObj.getString("errmsg");
				Exception e = new Exception(err);
				throw e;
			}
		}
		return token;
	}

	@Override
	public String wechatSession(String code) throws Exception {
		String openid = null;
		Map<String, Object> result = HttpUtil.doGet(String.format(WX_URL_SESSION, appid, appSecret, code));
		if((Integer) result.get("code") == 200) {
			JSONObject rspObj = JSONObject.parseObject((String) result.get("response"));
			try {
				openid = rspObj.getString("openid");
				UserExample ue = new UserExample();
				ue.createCriteria().andOpenidEqualTo(openid);
				List<User> userList = userMapper.selectByExample(ue);
				if(userList.size() == 0) {
					User user = new User();
					user.setOpenid(openid);
					user.setCreateUser(1L);
					user.setCreateDate(new Date());
					userMapper.insertSelective(user);
				} else {
					User user = userList.get(0);
					user.setUpdateUser(1L);
					user.setUpdateDate(new Date());
					userMapper.updateByPrimaryKeySelective(user);
				}
			} catch(Exception e1) {
				try {
					String err = "ErrCode:" + rspObj.getInteger("errcode") + " ErrMsg:" + rspObj.getString("errmsg");
					Exception e = new Exception(err);
					throw e;
				} catch(NullPointerException e2) {
					throw e1;
				}
			}
		}	
		return openid;
	}

}
