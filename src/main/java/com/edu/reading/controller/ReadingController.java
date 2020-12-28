package com.edu.reading.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.ResultCodeEnum;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.service.ReadingService;

@RestController
@RequestMapping("reading")
public class ReadingController extends BaseController {
	@Autowired
	private ReadingService readingService;
	
	private Logger logger = LoggerFactory.getLogger(ReadingController.class);
	
	@GetMapping("mine")
	@ResponseBody
	public  RestResult<Map<String,Object>> get(@RequestParam String openid) {
		Map<String, Object> result = readingService.mine(openid);
		if(result.size() > 0) {
			return ResultBuilder.buildSuccessResult("查询我的帐户成功", result);
		} else {
			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
		}
	}
}