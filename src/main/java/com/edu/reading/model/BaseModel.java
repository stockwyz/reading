package com.edu.reading.model;

import java.util.Date;

import com.edu.reading.model.uid.Uid;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseModel {
	@Uid
	protected Long id;
	protected Long createUser;
	protected Date createDate;
	protected Long updateUser;
	protected Date updateDate;
}
