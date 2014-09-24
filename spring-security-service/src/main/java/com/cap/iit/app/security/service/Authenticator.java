package com.cap.iit.app.security.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public interface Authenticator {
	void createSession(UsernamePasswordAuthenticationToken token);
	Authentication authenticate(UsernamePasswordAuthenticationToken token);
	void logout();
}
