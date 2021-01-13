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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "语英阅读接口")
@RestController
@RequestMapping("reading")
public class ReadingController extends BaseController {
	@Autowired
	private ReadingService readingService;
	
	private Logger logger = LoggerFactory.getLogger(ReadingController.class);
	
	@ApiOperation("我的班级(个人中心)")
	@PostMapping("mine")
	@ResponseBody
	public  RestResult<Map<String,Object>> mine(@RequestParam @ApiParam(value = "用户的openid", required = true) String openid) {
		Map<String, Object> result = readingService.mine(openid);
		if(result.size() > 0) {
			return ResultBuilder.buildSuccessResult("查询我的帐户成功", result);
		} else {
			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
		}
	}
	
	@ApiOperation("修改个人信息")
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
	
	@ApiOperation("首页接口")
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
	
	@ApiOperation("英语/语文栏目查询接口")
	@PostMapping("subject")
	@ResponseBody
	public  RestResult<Map<String, Object>> subject(@RequestBody SubjectQueryDto subjectDto) {
		if(ObjectUtils.isEmpty(subjectDto.getSubject())) {
			return ResultBuilder.buildErrorResult("查询科目不能为空");
		}
		
		if(ObjectUtils.isEmpty(subjectDto.getType())) {
			return ResultBuilder.buildErrorResult("查询课本类型不能为空");
		}
		
		Map<String, Object> result = null;
		try {
			result = readingService.querySubject(subjectDto);
			return ResultBuilder.buildSuccessResult("查询科目课本成功", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultBuilder.buildErrorResult("查询科目课本失败.Err:" + e.getLocalizedMessage());
		}
	}
	
	@ApiOperation("英语/语文课文查询接口")
	@PostMapping("lesson")
	@ResponseBody
	public  RestResult<List<Lesson>> lessson(@RequestParam @ApiParam(value = "目录ID或课程ID", required = true) Long id) {
		List<Lesson> result = null;
		try {
			result = readingService.queryLesson(id);
			return ResultBuilder.buildSuccessResult("查询课程成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBuilder.buildErrorResult("查询课程失败.Err:" + e.getLocalizedMessage());
		}
	}
}