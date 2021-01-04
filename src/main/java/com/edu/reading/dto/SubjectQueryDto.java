package com.edu.reading.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubjectQueryDto {
	private String openid;
	
	private Long classId;
	
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
	private String grade;
	
	/**
	 * 学期 1.上学期 2.下学期
	 */
	private Integer term;
	
}
