package com.edu.reading.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pageNo;

	private Integer pageSize;
}
