package com.edu.reading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.ResultCodeEnum;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.dto.ActivityQueryDto;
import com.edu.reading.model.Activity;
import com.edu.reading.service.ActivityService;

@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController {
	@Autowired
	private ActivityService activityService;
	
	@GetMapping("all")
	@ResponseBody
	public  RestResult<List<Activity>> all() {
		List<Activity> result = activityService.getAll();
		if(!ObjectUtils.isEmpty(result)) {
			return ResultBuilder.buildSuccessResult("查询活动成功", result);
		} else {
			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
		}
	}
	
	@PostMapping("mine")
	@ResponseBody
	public  RestResult<List<Activity>> mine(@RequestBody ActivityQueryDto dto) {
		List<Activity> result = activityService.mine(dto);
		if(!ObjectUtils.isEmpty(result)) {
			return ResultBuilder.buildSuccessResult("查询我的活动成功", result);
		} else {
			return ResultBuilder.buildResult(ResultCodeEnum.EMPTY, null);
		}
	}
}
