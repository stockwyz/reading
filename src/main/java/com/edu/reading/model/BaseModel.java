package com.edu.reading.model;

import java.util.Date;

import com.edu.reading.model.uid.Uid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseModel {
	@Uid
	protected Long id;
	@ApiModelProperty(hidden = true)
	protected Long createUser;
	@ApiModelProperty(hidden = true)
	protected Date createDate;
	@ApiModelProperty(hidden = true)
	protected Long updateUser;
	@ApiModelProperty(hidden = true)
	protected Date updateDate;
}
