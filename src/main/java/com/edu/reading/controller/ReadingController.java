package com.edu.reading.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.ResultCodeEnum;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.dto.SubjectQueryDto;
import com.edu.reading.model.Lesson;
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
	
	@PostMapping("subject")
	@ResponseBody
	public  RestResult<List<Lesson>> subject(@RequestBody SubjectQueryDto subjectDto) {
		if(ObjectUtils.isEmpty(subjectDto.getSubject())) {
			return ResultBuilder.buildErrorResult("查询科目不能为空");
		}
		
		if(ObjectUtils.isEmpty(subjectDto.getType())) {
			return ResultBuilder.buildErrorResult("查询课本类型不能为空");
		}
		
		List<Lesson> result = null;
		try {
			result = readingService.querySubject(subjectDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(result.size() > 0) {
//			return ResultBuilder.buildSuccessResult("查询科目课本成功", result);
//		} else {
//			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
//		}
		return null;
	}
}