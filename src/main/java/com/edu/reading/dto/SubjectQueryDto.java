package com.edu.reading.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubjectQueryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 微信openid
	 */
	private String openid;
	
    /**
     * 1.英语 2.语文
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
	 * 学期  0上学期 1下学期
	 */
	private Integer term = -1;
	
	//学校ID
	private Long schoolId;
	
	// 出版商ID
	private Long publisherId;
	
	// 班级ID
	private Long classId;
	
}
