package com.edu.reading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.service.CodeService;

import io.swagger.annotations.Api;

@Api(hidden = true)
@RestController
@RequestMapping("/code/")
public class CodeController extends BaseController {

	@Autowired
	private CodeService service;

	/**
	 * 
	 * 方法: createCode <br>
	 * 描述: 统一生成流水号接口 <br>
	 * 作者: zhy<br>
	 * 时间: 2020年4月9日 上午10:49:26
	 * 
	 * @param prefix
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody
	public RestResult<String> get(@RequestParam("prefix") String prefix) {
		try {
			return ResultBuilder.buildSuccessResult("获取编码成功", service.getCode(prefix));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBuilder.buildErrorResult("获取编码失败");
		}
	}
}
