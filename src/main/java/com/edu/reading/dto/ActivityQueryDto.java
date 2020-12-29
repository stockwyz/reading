package com.edu.reading.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActivityQueryDto {
	private Long userId;
	private String openid;
	private Integer finished;
}
