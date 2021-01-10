package com.edu.reading.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.edu.reading.common.tree.TreeEntry;
import com.edu.reading.common.tree.TreeLevelParser;
import com.edu.reading.common.tree.config.TreeConfig;
import com.edu.reading.mapper.DirectoryMapper;
import com.edu.reading.model.Directory;
import com.edu.reading.model.DirectoryExample;
import com.edu.reading.service.DirectoryService;

@Service
public class DirectoryServiceImpl extends TreeEntry<Directory> implements DirectoryService {
	
	private boolean initEmployee;
	
	@Autowired
	private DirectoryMapper directoryMapper;
	
	@Override
	public List<Directory> getDataList() {
		// TODO Auto-generated method stub
		DirectoryExample ex = new DirectoryExample();
		ex.setOrderByClause("parent_id");
		return directoryMapper.selectByExample(ex);
	}

	@Override
	public List<Directory> initTreeList() {
		getParser().parseRelation(this, getDataList());
		if(!isInitEmployee()) {
//			attachEmployeeToDept(getTreeList());
			getParser().breadcrumb(getTreeList().get(0), null);
			setInitEmployee(true);
		}
		return getTreeList();
	}

	@Override
	public synchronized boolean reload() {
		setInitialized(false);
		setTreeList(new ArrayList<Directory>());
		initTreeList();
		setInitEmployee(false);
		if(!isInitEmployee()) {
//			attachEmployeeToDept(getTreeList());
			setInitEmployee(true);
		}
		return initEmployee;
	}

	//把员工按部门编号附加到组织树中各结点
//	public void attachEmployeeToDept(List<? extends TreeNode> list) {
//		if(list.isEmpty()) {
//			return ;
//		}
//		
//		for(TreeNode org : list) {
//			attachEmployeeToDept(org.getChildren());
//			//查询本组织下属员工并映射成BIOrg形式放入children列表 
//			DirectoryExample ex = new DirectoryExample();
//			ex.createCriteria().andDeptIdEqualTo(String.valueOf(org.getId())).andEnabledEqualTo(1);
//			List<SysUser> employeeList = sysUserMapper.selectByExample(ex);
//			if(employeeList != null && !employeeList.isEmpty()) {
//				org.setLeaf(false);
//				List<OrgEmpNodeDto> dtoLst = new ArrayList<>();
//				for(SysUser employee : employeeList) {
//					OrgEmpNodeDto empDto = new OrgEmpNodeDto();
//					empDto.setId(employee.getId());
//					empDto.setUserId(employee.getId());
//					empDto.setName(employee.getName());
//					empDto.setWorkNo(employee.getJobnumber());
//					empDto.setParentid(org.getId());
//					empDto.setLeaf(true);
//					dtoLst.add(empDto);
//				}
//				org.getChildren().addAll(dtoLst);
//			}
//		}
//	}

	public boolean isInitEmployee() {
		return initEmployee;
	}

	public void setInitEmployee(boolean initEmployee) {
		this.initEmployee = initEmployee;
	}

	@Autowired
	@Qualifier("DeptEmpTreeConfig")
	@Override
	public void setConfig(TreeConfig config) {
		// TODO Auto-generated method stub
		this.config = config;
	}

	@Autowired
	@Override
	public void setParser(TreeLevelParser<Directory> parser) {
		// TODO Auto-generated method stub
		this.parser = parser;
	}
}
