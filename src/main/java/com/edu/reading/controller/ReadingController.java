package com.edu.reading.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.ResultCodeEnum;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.model.User;
import com.edu.reading.service.ReadingService;

@RestController
@RequestMapping("reading")
public class ReadingController extends BaseController {
	@Autowired
	private ReadingService readingService;
	
	private Logger logger = LoggerFactory.getLogger(ReadingController.class);
	
	@PostMapping("mine")
	@ResponseBody
	public  RestResult<Map<String,Object>> mine(@RequestParam String openid) {
		Map<String, Object> result = readingService.mine(openid);
		if(result.size() > 0) {
			return ResultBuilder.buildSuccessResult("查询我的帐户成功", result);
		} else {
			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
		}
	}
	
	@PostMapping("update/person-info")
	@ResponseBody
	public  RestResult<Map<String,Object>> updatePersonInfo(@RequestBody User user) {
		Integer count = readingService.updatePersonInfo(user);
		if(count == 1) {
			return ResultBuilder.buildSuccessResult("个人信息更新成功", null);
		} else {
			return ResultBuilder.buildErrorResult("个人信息更新失败");
		}
	}
	
	@PostMapping("home")
	@ResponseBody
	public  RestResult<Map<String,Object>> home(@RequestParam String openid) {
		Map<String, Object> result = readingService.mine(openid);
		if(result.size() > 0) {
			return ResultBuilder.buildSuccessResult("查询我的帐户成功", result);
		} else {
			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
		}
	}
}