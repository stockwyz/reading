package com.edu.reading.util;

import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import com.edu.reading.controller.BaseController;

@Aspect
@Configuration
public class ControllerAop {
	@Autowired  
	private HttpServletRequest request; 
	
	@Around(value = "execution(public * com.reignwood.evaluate.controller.*Controller.*(..))")
	public Object userAop(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("拦截到了" + jp.getSignature().getName() +"方法..."); 
		String userId = request.getHeader("userid");
		String userName = request.getHeader("username");
		// BaseController
		Class<?> clazz = jp.getTarget().getClass().getSuperclass();
		if(BaseController.UserLocal.get() == null) {
			BaseController.UserLocal.set(new HashMap<String, String>());
		}
		
		if(!ObjectUtils.isEmpty(userId)) {
			// userid
			BaseController.UserLocal.get().put("userId", userId);		
		}
		if(!ObjectUtils.isEmpty(userName)) {
			// username
			Base64.Decoder decoder = Base64.getDecoder();
			userName = new String(decoder.decode(userName), "UTF-8");
			BaseController.UserLocal.get().put("userName", userName);	
		}
		
		Object[] args = jp.getArgs();		
		return jp.proceed(args);	
	}
}
