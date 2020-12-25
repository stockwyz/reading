package com.edu.reading.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reading.common.result.ResultBuilder;
import com.edu.reading.common.result.model.RestResult;
import com.edu.reading.util.HttpUtil;

@RestController
@RequestMapping("sys")
public class SysController extends BaseController {
	// @Autowired
	// private DingService dingService;
	//

	private Logger logger = LoggerFactory.getLogger(SysController.class);
	

	@GetMapping("/openid")
	public void getAccessToken() {
		System.out.println(HttpUtil.doGet("https://api.weixin.qq.com/sns/jscode2session?appid=wxee10d2f412a62daa&secret=0bd2eb25d25323a3519a08e3328a6d74&js_code=051GLPkl2N6id641qQll2FiAUa4GLPkx&grant_type=authorization_code"));
			
//		System.out.println(HttpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxee10d2f412a62daa&secret=0bd2eb25d25323a3519a08e3328a6d74"));
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

	// @PostMapping("/tree/org-emp")
	// @ResponseBody
	// public RestResult<List<SysDept>> orgEmpTree() {
	//
	// }

}