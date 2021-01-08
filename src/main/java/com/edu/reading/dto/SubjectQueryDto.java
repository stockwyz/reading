package com.edu.reading.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubjectQueryDto {
	/**
	 * 微信openid
	 */
	private String openid;
	
    /**
     * 1.数学 2.语文
     */
	private Integer subject;
	
    /**
     * 1.课本 2绘本
     */
	private Integer type;
	
	/**
	 * 年级
	 */
	private String grade = "1年级";
	
	/**
	 * 学期  0上学期 1下学期
	 */
	private Integer term = 0;
	
	// 出版商ID
	private Long publisherId;
	
	private Long classId;
	
}
