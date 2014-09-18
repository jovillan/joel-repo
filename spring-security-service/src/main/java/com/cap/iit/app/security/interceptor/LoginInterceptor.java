package com.cap.iit.app.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
	        HttpServletResponse response, Object handler) throws Exception {
		System.out.println("-----LoginInterceptor-----");
		
	    //TODO after logout accessing previous pages will redirect to login
			//if session is null, or if logout is trigerred, pressing back will redirect to login
	    
		System.out.println("*****end of login interceptor*****");
		return true;
	 }
}
