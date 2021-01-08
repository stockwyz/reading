package com.edu.reading.common.tree;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
//import com.reignwood.sicily.service.model.user.Menus;
//import com.reignwood.sicily.service.model.user.Orgnodes;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "classType")
//@JsonSubTypes({ @Type(value = Menus.class, name = "menus"), @Type(value = Orgnodes.class, name = "orgnodes") })
public interface TreeNode extends Comparable<TreeNode>, Cloneable {

	Long getId();

	String getCode();

	String getName();

	TreeNode getParent();

	void setParent(TreeNode parent);

	List<TreeNode> getChildren();

	void setChildren(List<TreeNode> children);

	String getLevelCode();

	Integer getDisplayorder();

	boolean isLeaf();

	void setLeaf(boolean leaf);

	boolean isExpanded();

	boolean isReserved();

	void setReserved(boolean reserved);

	void setBreadcrumb(String bc);

	public Object clone() throws CloneNotSupportedException;
}
