package com.edu.reading.service.impl;

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
import com.edu.reading.mapper.SchoolGradeMapper;
import com.edu.reading.mapper.SchoolPublisherMapper;
import com.edu.reading.mapper.UserMapper;
import com.edu.reading.model.Lesson;
import com.edu.reading.model.SchoolGrade;
import com.edu.reading.model.SchoolGradeExample;
import com.edu.reading.model.SchoolPublisher;
import com.edu.reading.model.SchoolPublisherExample;
import com.edu.reading.model.User;
import com.edu.reading.model.UserExample;
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
	public List<Lesson> querySubject(SubjectQueryDto subjectDto) throws Exception {
		Map<String, String> param = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		Long schoolId = 0L;
		
		if(subjectDto.getType() == 1) {
			// 查询课本
			// 1.按用户所在学校,年级查询目录
			if(!ObjectUtils.isEmpty(subjectDto.getOpenid())) {
				// openid不为空, 查询已登录用户
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
				if(schoolId == 0L && ObjectUtils.isEmpty(subjectDto.getGrade())) {
					// 用户未选择所属学校并且查询参数年级为空时, 取默认学校的最大年级
					SchoolGradeExample ex = new SchoolGradeExample();
					ex.createCriteria().andSchoolIdEqualTo(schoolId);
					ex.setOrderByClause("grade desc");
					List<SchoolGrade> sgLst = schoolGradeMapper.selectByExample(ex);
					if(!ObjectUtils.isEmpty(sgLst)) {
						subjectDto.setGrade(sgLst.get(0).getGrade());
					}
				}
			} else {
				// openid为空,游客
				// 取默认学校
				SchoolGradeExample ex = new SchoolGradeExample();
				ex.createCriteria().andSchoolIdEqualTo(schoolId);
				ex.setOrderByClause("grade desc");
				// 取默认学校的最大年级
				List<SchoolGrade> sgLst = schoolGradeMapper.selectByExample(ex);
				if(!ObjectUtils.isEmpty(sgLst)) {
					subjectDto.setGrade(sgLst.get(0).getGrade());
				}

			}
			
			// 2.查询学校所用教材
			SchoolPublisherExample spe = new SchoolPublisherExample();
			spe.createCriteria().andSchoolIdEqualTo(schoolId).andGradeEqualTo(subjectDto.getGrade());
			List<SchoolPublisher> spLst = schoolPublisherMapper.selectByExample(spe);
			if(ObjectUtils.isEmpty(spLst)) {
				throw new Exception("未找到学校所配套的教材!");
			}
			subjectDto.setPublisherId(spLst.get(0).getPublisherId());
			
			// 3.查询所在年级教材目录
			directoryMapper.selectByExample(null);
		} else {
			//查询绘文
			
		}
		
		
		
		if(ObjectUtils.isEmpty(subjectDto.getGrade())) {
			// 年级为空

		}
		
		if(ObjectUtils.isEmpty(subjectDto.getTerm())) {
			// 默认上学期
			subjectDto.setTerm(0);
		}

		return null;
	}
}
