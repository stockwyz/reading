package com.edu.reading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.service.SysService;

@RestController
@RequestMapping("sys")
public class SysController extends BaseController {
	@Autowired
	private SysService sysService;

	private Logger logger = LoggerFactory.getLogger(SysController.class);
	

	@GetMapping("/openid")
	public RestResult<String> getOpenid(@RequestParam String code) {
		try {
			return ResultBuilder.buildSuccessResult("获取OpenId成功", sysService.wechatSession(code));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBuilder.buildErrorResult("获取OpenId失败:" + e.getMessage());
		}
	}
	// @GetMapping("/openid")
	// public RestResult<String> getOpenId(@RequestParam("code") String code) {
	// String result = wxService.getUserOpenId(code);
	// if(StringUtils.isNotEmpty(result)) {
	// return ResultBuilder.buildSuccessResult("获取OpenId成功", result);
	// } else {
	// return ResultBuilder.buildErrorResult("接口发生异常");
	// }
	// }

	// @RequestMapping(value = "dept", method = RequestMethod.GET)
	// public RestResult<List<Department>> getDept(@RequestParam(value = "parent",
	// required = false) String parentId,
	// @RequestParam(value = "fetchChild", required = false, defaultValue = "false")
	// Boolean fetchChild)
	// {
	//
	// }

	// /**
	// * 获取当前钉钉用户
	// * @author zht
	// * @param authCode
	// * @return
	// */
	// @PostMapping("session-user")
	// @ResponseBody
	// public RestResult<Map<String, Object>> sessionUserInfo(@RequestParam String
	// authCode)
	// {
	// RestResult<Map<String, Object>> result = null;
	// try {
	// Map<String, Object> userInfo = dingService.getSessionUserInfo(authCode);
	// if(Long.parseLong(userInfo.get("code").toString()) == 0L) {
	// result = ResultBuilder.buildSuccessResult("获取用户会话成功", userInfo);
	// } else {
	// result = ResultBuilder.buildErrorResult("获取用户会话成功失败:" + userInfo.get("msg"));
	// }
	// } catch (ApiException e) {
	// e.printStackTrace();
	// result = ResultBuilder.buildErrorResult("获取用户会话成功失败:" +
	// e.getLocalizedMessage());
	// }
	// return result;
	// }

	@GetMapping("/version")
	@ResponseBody
	public RestResult<? extends Object> visitor() {
		return ResultBuilder.buildSuccessResult("1.0");
	}
}