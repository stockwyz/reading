package com.edu.reading.common.tree.config;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreeConfig {
	private Boolean cascade;
	// set nodecode method
	private Method nodeCodeMethod;
	// setLevel method
	private Method levelMethod;
	// getParentId method
	private Method parentMethod;
	// addChild method
	private Method addChildMethod;
	// setLeaf method
	private Method leafMethod;
	// getChildren method
	private Method childrenMethod;
	// setParent method
	private Method settingParentMethod;
	//同级排序
	private Boolean sortSameLevel = false;
	//属性值覆盖(以key为名称的属性值->以value为名称的属性值)
	public Map<String, String> overrides;
	//输出json时(key为名称的POJO属性值会以value为key转出json)
	public Map<String, String> mappings;
	/**
	 * 按父子关系字段或层次码表示树型结型
	 */
	private LevelType levelType;
	
	private String clazzType;
	
	private String dictName;

	public boolean checkNotNull() {
		//按父结点字段表示层次
		if(levelType == LevelType.ParentRef) {
			if(parentMethod == null)
				return false;
		}
		//levelMethod, 
		return ObjectUtils.allNotNull(clazzType, settingParentMethod, addChildMethod, childrenMethod, leafMethod);
	}
}
