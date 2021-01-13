package com.edu.reading.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.edu.reading.dto.SubjectQueryDto;
import com.edu.reading.mapper.ClassesMapper;
import com.edu.reading.mapper.DirectoryMapper;
import com.edu.reading.mapper.LessonMapper;
import com.edu.reading.mapper.SchoolGradeMapper;
import com.edu.reading.mapper.SchoolPublisherMapper;
import com.edu.reading.mapper.UserMapper;
import com.edu.reading.model.Directory;
import com.edu.reading.model.Lesson;
import com.edu.reading.model.LessonExample;
import com.edu.reading.model.LessonExample.Criteria;
import com.edu.reading.model.SchoolGrade;
import com.edu.reading.model.SchoolGradeExample;
import com.edu.reading.model.SchoolPublisher;
import com.edu.reading.model.SchoolPublisherExample;
import com.edu.reading.model.User;
import com.edu.reading.model.UserExample;
import com.edu.reading.service.DirectoryService;
import com.edu.reading.service.ReadingService;

@Service
public class ReadingServiceImpl implements ReadingService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DirectoryMapper directoryMapper;
	@Autowired
	private ClassesMapper classesMapper;
	@Autowired
	private SchoolPublisherMapper schoolPublisherMapper;	
	@Autowired
	private SchoolGradeMapper schoolGradeMapper;	
	@Autowired
	private DirectoryService directoryService;
	@Autowired
	private LessonMapper lessonMapper;	
	
	/**
	 * 底部导航-我的(班级)
	 */
	@Override
	public Map<String, Object> mine(String openId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<>();
		UserExample ue = new UserExample();
		ue.createCriteria().andOpenidEqualTo(openId);
		List<User> lst = userMapper.selectByExample(ue);
		if(!ObjectUtils.isEmpty(lst)) {
			User user = lst.get(0);
			result.put("user", user);
			result.put("classList", classesMapper.getClassByUserId(user.getId()));
		}
		return result;
	}

	/**
	 * 底部导航-首页
	 */
	@Override
	public Map<String, Object> home(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 我的(班级)更新个人信息
	 */
	@Override
	public Integer updatePersonInfo(User user) {
		UserExample ue = new UserExample();
		if(user.getId() == null) {
			ue.createCriteria().andOpenidEqualTo(user.getOpenid());
			List<User> lst = userMapper.selectByExample(ue);
			if(!ObjectUtils.isEmpty(lst)) {
				user.setUpdateUser(lst.get(0).getId());
			}			
		} else {
			user.setUpdateUser(user.getId());
		}

		user.setUpdateDate(new Date());
		UserExample ex = new UserExample();
		ex.createCriteria().andOpenidEqualTo(user.getOpenid());
		return userMapper.updateByExampleSelective(user, ex);
	}

	/**
	 * 底部导航-查询英语,语文课本,绘本栏目
	 */
	@Override
	public Map<String, Object> querySubject(SubjectQueryDto subjectDto) throws Exception {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		Long schoolId = 0L;
		List<SchoolGrade> allGradeLst = null;
		if(subjectDto.getType() == 1) {
			// 查询课本
			// 1.按用户所在学校,年级查询目录
			if(subjectDto.getOpenid() != null) {
				if(subjectDto.getSchoolId() == null) {
					// 查询已授权绑定用户的学校ID
					UserExample ue = new UserExample();
					ue.createCriteria().andOpenidEqualTo(subjectDto.getOpenid());
					List<User> lst = userMapper.selectByExample(ue);
					if(ObjectUtils.isEmpty(lst)) {
						throw new Exception("openid:" + subjectDto.getOpenid() + "没有对应的授权用户!");
					}	
					
					User user = lst.get(0);
					if(user.getSchoolId() != null) {
						schoolId = user.getSchoolId();
					}					
				}

				allGradeLst = getAllGradeBySchoolId(schoolId);
				if(schoolId == 0L || ObjectUtils.isEmpty(subjectDto.getGrade())) {
					// 用户未选择所属学校并且查询参数年级为空时, 取默认学校的最大年级
					if(!ObjectUtils.isEmpty(allGradeLst)) {
						subjectDto.setGrade(allGradeLst.get(0).getGrade());
					} else {
						subjectDto.setGrade("1年级");
					}
				}
			} else {
				// openid为空,游客取默认学校(id:0)
				// 取默认学校的最大年级
				allGradeLst = getAllGradeBySchoolId(schoolId);
				if(!ObjectUtils.isEmpty(allGradeLst)) {
					subjectDto.setGrade(allGradeLst.get(0).getGrade());
				} else {
					subjectDto.setGrade("1年级");
				}
			}
			// 该学校所有年级
			result.put("grades", allGradeLst);
			
			// 2.查询学校所用教材
			SchoolPublisherExample spe = new SchoolPublisherExample();
			spe.createCriteria().andSchoolIdEqualTo(schoolId).andGradeEqualTo(subjectDto.getGrade());
			List<SchoolPublisher> spLst = schoolPublisherMapper.selectByExample(spe);
			if(ObjectUtils.isEmpty(spLst)) {
				throw new Exception("未找到学校所配套的教材!");
			}
			subjectDto.setPublisherId(spLst.get(0).getPublisherId());
			
			// 3.查询所在年级教材目录
			StringBuffer sb = new StringBuffer();
			sb.append("publisherId:" + subjectDto.getPublisherId()).append("(@)grade:" + subjectDto.getGrade());
			if(subjectDto.getTerm() != -1) {
				sb.append("(@)term:" + subjectDto.getTerm());
			}
			String[] filters = new String[] {sb.toString()};
			List<Directory> subDirectory = new ArrayList<>();
			
			DirectoryServiceImpl dsl = (DirectoryServiceImpl) directoryService;
			dsl.initTreeList();
			dsl.locateObjectInTree(dsl.getTreeList(), filters, false, subDirectory);
			result.put("content", subDirectory);

		} else {
			//查询绘本
			// 1.按用户所在学校,年级查询目录
			if(subjectDto.getOpenid() != null) {
				if(subjectDto.getSchoolId() == null) {
					// 查询已授权绑定用户的学校ID
					UserExample ue = new UserExample();
					ue.createCriteria().andOpenidEqualTo(subjectDto.getOpenid());
					List<User> lst = userMapper.selectByExample(ue);
					if(ObjectUtils.isEmpty(lst)) {
						throw new Exception("openid:" + subjectDto.getOpenid() + "没有对应的授权用户!");
					}	
					
					User user = lst.get(0);
					if(user.getSchoolId() != null) {
						schoolId = user.getSchoolId();
					}					
				}

				allGradeLst = getAllGradeBySchoolId(schoolId);
				if(schoolId == 0L || ObjectUtils.isEmpty(subjectDto.getGrade())) {
					// 用户未选择所属学校并且查询参数年级为空时, 取默认学校的最大年级
					if(!ObjectUtils.isEmpty(allGradeLst)) {
						subjectDto.setGrade(allGradeLst.get(0).getGrade());
					} else {
						subjectDto.setGrade("1年级");
					}
				}
			} else {
				// openid为空,游客取默认学校(id:0)
				// 取默认学校的最大年级
				allGradeLst = getAllGradeBySchoolId(schoolId);
				if(!ObjectUtils.isEmpty(allGradeLst)) {
					subjectDto.setGrade(allGradeLst.get(0).getGrade());
				} else {
					subjectDto.setGrade("1年级");
				}
			}
			// 该学校所有年级
			result.put("grades", allGradeLst);
			
			// 查询所在年级绘本
			LessonExample le = new LessonExample();
			Criteria cri = le.createCriteria();
			cri.andSchoolIdEqualTo(schoolId).andGradeEqualTo(subjectDto.getGrade()).andPublishedEqualTo(1);
			if(subjectDto.getTerm() != -1) {
				cri.andTermEqualTo(subjectDto.getTerm());
			}
			result.put("content", lessonMapper.selectByExample(le));			
		}
		
		// 记录当前的查询参数
		param.put("schoolId", schoolId);
		param.put("grade", subjectDto.getGrade());
		param.put("term", subjectDto.getTerm());
		result.put("param", param);
		return result;
	}
	
	private List<SchoolGrade>  getAllGradeBySchoolId(Long schoolId) {
		SchoolGradeExample ex = new SchoolGradeExample();
		ex.createCriteria().andSchoolIdEqualTo(schoolId);
		ex.setOrderByClause("grade desc");
		List<SchoolGrade> sgLst = schoolGradeMapper.selectByExample(ex);
		return sgLst;
	}

	@Override
	public List<Lesson> queryLesson(Long id) throws Exception {
		LessonExample le = new LessonExample();
		le.createCriteria().andPublishedEqualTo(1);
		le.or().andIdEqualTo(id).andDirectoryIdEqualTo(id);
		return lessonMapper.selectByExample(le);
	}
}
