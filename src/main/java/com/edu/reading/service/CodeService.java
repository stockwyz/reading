package com.edu.reading.service;

/**
 * 
 * 类: CodeService <br>
 * 描述: 编码相关接口 <br>
 * 作者: zhy<br>
 * 时间: 2020年4月9日 下午1:37:36
 */
public interface CodeService {

	/**
	 * 
	 * 方法: getCode <br>
	 * 描述: 获取最新编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2020年4月9日 下午1:37:25
	 * @param prefix
	 * @return
	 */
	String getCode(String prefix);
}
