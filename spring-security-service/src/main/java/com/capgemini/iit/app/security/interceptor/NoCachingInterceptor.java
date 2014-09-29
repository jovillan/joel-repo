package com.capgemini.iit.app.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NoCachingInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(NoCachingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
	        HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("login interceptor start process");
		preventBrowserCaching(response);
		logger.info("login interceptor is done");
		return true;
	 }
	
	private void preventBrowserCaching(HttpServletResponse response) {
		
		logger.info("prevent browser caching..");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		logger.info("browser caching prevention completed");
	}
}
