package com.edu.reading.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value="栏目查询接口参数体",description="栏目查询接口参数体")
public class SubjectQueryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 微信openid
	 */
	@ApiModelProperty(value = "微信openid--oIQbX5PzpA7KRhasCO71otDj4MQ8", required = false)
	private String openid;
	
    /**
     * 1.英语 2.语文
     */
	@ApiModelProperty(value = "科目--1.英语 2.语文", allowableValues = "range[1,2]", required = true)
	private Integer subject;
	
    /**
     * 1.课本 2绘本
     */
	@ApiModelProperty(value = "类型--1.课本 2绘本 -1所有", allowableValues = "range[-1,1,2]", required = true)
	private Integer type;
	
	/**
	 * 年级
	 */
	@ApiModelProperty(value = "年级", required = false)
	private String grade;
	
	/**
	 * 学期  0上学期 1下学期
	 */
	@ApiModelProperty(value = "学期--0上学期 1下学期", allowableValues = "range[0,1]", required = false)
	private String term;
	
	//学校ID
	@ApiModelProperty(hidden =  true)
	private Long schoolId;
	
	// 出版商ID
	@ApiModelProperty(hidden =  true)
	private Long publisherId;
	
	// 班级ID
	@ApiModelProperty(hidden =  true)
	private Long classId;
	
}
