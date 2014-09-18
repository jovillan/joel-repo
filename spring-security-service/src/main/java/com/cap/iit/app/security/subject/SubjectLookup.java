package com.cap.iit.app.security.subject;

import com.cap.iit.app.security.config.SecurityConfigType;

public class SubjectLookup {
	public Subject getSubjectByType(SecurityConfigType configType){
		
		switch (configType) {
		case SPRING:
			return new SpringSubject();
		case SHIRO:
			return new ShiroSubject();
		default:
			return null;
		}
		
	}
}
