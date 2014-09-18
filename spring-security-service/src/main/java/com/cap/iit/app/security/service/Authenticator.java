package com.cap.iit.app.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface Authenticator {
//	boolean login(String corpId, String password);
//	boolean login(HttpServletRequest request);
	Authentication login(HttpServletRequest request);
	void logout(HttpServletRequest request);
}
