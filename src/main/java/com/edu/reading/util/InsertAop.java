package com.edu.reading.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.edu.reading.model.uid.Uid;

/**
 * 原想使用MybatisPlusAutoConfiguration无感生成UID
 * 但该类有@ConditionalOnSingleCandidate(DataSource.class)标注.
 * 本系统用Baidu的UID生成ID,此框架使用一个WorkNode表,占用一个数据源;系统还有一
 * 个业务数据源.所以不满足ConditionalOnSingleCandidate.用AOP方法注入ID
 * 
 * @author zht
 *
 */
@Aspect
@Configuration
public class InsertAop {
	@Autowired
	private CachedUidGenerator generator;

	@Around(value = "execution(public * com.edu.reading..*.*Mapper.insert*(..))")
	public Object selectAop(ProceedingJoinPoint jp) {
		Object result = null;
		try {
			Object[] args = jp.getArgs();
			if (args != null && args.length == 1) {
				if (Iterable.class.isAssignableFrom(args[0].getClass())) {
					// insert方法参数是多对象集合
					List<Object> lst = new ArrayList<>();
					@SuppressWarnings("unchecked")
					Iterator<Object> it = ((Iterable<Object>) args[0]).iterator();
					while (it.hasNext()) {
						Object obj = it.next();
						lst.add(setId(obj, obj.getClass()));
					}
					result = lst;
				} else {
					// insert方法参数是单一值对象
					result = setId(args[0], args[0].getClass());
				}
			}
			// 执行目标方法
			result = jp.proceed(jp.getArgs());
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	private Object setId(Object obj, Class<?> clazz) {
		if (clazz == null || clazz.equals(Object.class)) {
			return null;
		}

		Object vid = null;
		// 处理单一对象所有属性,查找Uid标识
		Field[] fields = clazz.getDeclaredFields();
		Boolean idFound = false;
		for (Field field : fields) {
			try {
				if (!field.isAnnotationPresent(Uid.class)) {
					continue;
				}
				// 找到Uid标识的属性
				idFound = true;
				// 生成Uid值
				vid = generator.getUID();
				field.setAccessible(true);
				if (field.get(obj) == null) {
					field.set(obj, vid);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (idFound) {
			return vid;
		}

		// 未找到Uid标识的属性继续向上处理父类
		return setId(obj, clazz.getSuperclass());
	}
}
