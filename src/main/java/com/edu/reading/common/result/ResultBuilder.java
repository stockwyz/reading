package com.edu.reading.common.result;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.github.pagehelper.PageInfo;
import com.edu.reading.common.result.model.RestPageResult;
import com.edu.reading.common.result.model.RestResult;

/**
 * rest接口返回类builder
 * 
 * @author zht
 */
public class ResultBuilder {

	public static <T extends Object> RestResult<T> buildResult(IResultCode resultCode, T resultData) {
		Assert.notNull(resultCode, "the resultCode can not be null");
		RestResult<T> rr = new RestResult<>(resultCode.getCode(), resultCode.getCodeMsg(), resultData);
		return rr;
	}

	public static <T extends Object> RestResult<T> buildSuccessResult(T resultData) {
		return buildResult(ResultCodeEnum.SUCCESS, resultData);
	}

	public static <T extends Object> RestResult<T> buildSuccessResult(String successMsg, T resultData) {
		RestResult<T> rr = buildResult(ResultCodeEnum.SUCCESS, resultData);
		rr.setMessage(successMsg);
		return rr;
	}

	public static <T extends Object> RestResult<T> buildSuccessResult() {
		return buildResult(ResultCodeEnum.SUCCESS, null);
	}

	public static <T extends Object> RestResult<T> buildSuccessResult(String successMsg) {
		RestResult<T> rr = buildResult(ResultCodeEnum.SUCCESS, null);
		rr.setMessage(successMsg);
		return rr;
	}

	public static <T extends Object> RestResult<T> buildErrorResult(String errorMsg) {
		RestResult<T> rr = buildResult(ResultCodeEnum.ERROR, null);
		rr.setMessage(errorMsg);
		return rr;
	}

	public static <T extends Object> RestPageResult<T> buildPageResult(IResultCode resultCode, String msg,
			PageInfo<T> resultData) {
		Assert.notNull(resultCode, "the resultCode can not be null");
		if (resultData == null) {
			return new RestPageResult<T>(resultCode.getCode(),msg, null, 0, 0, 0l);
		}
		RestPageResult<T> rr = new RestPageResult<>(resultCode.getCode(),
				StringUtils.isBlank(msg) ? resultCode.getCodeMsg() : msg, resultData.getList(), resultData.getPageNum(),
				resultData.getPageSize(), resultData.getTotal());
		return rr;
	}

	public static <T extends Object> RestPageResult<T> buildSuccessPageResult(String successMsg,
			PageInfo<T> resultData) {
		RestPageResult<T> rr = buildPageResult(ResultCodeEnum.SUCCESS, successMsg, resultData);
		return rr;
	}

	public static <T extends Object> RestPageResult<T> buildErrorPageResult(String errorMsg) {
		RestPageResult<T> rr = buildPageResult(ResultCodeEnum.ERROR, errorMsg, null);
		return rr;
	}
}
