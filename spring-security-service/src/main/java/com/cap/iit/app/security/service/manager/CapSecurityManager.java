package com.cap.iit.app.security.service.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.iit.app.security.config.SecurityConfigType;
import com.cap.iit.app.security.subject.SubjectDelegate;
import com.cap.iit.app.security.subject.dto.SubjectDto;

@Component
public class CapSecurityManager {
	@Autowired
	private SubjectDelegate subjectService;
	
	//TODO return authentication token instead of boolean value
//	public boolean login(HttpServletRequest request) {
	public SubjectDto login(HttpServletRequest request) {
		
		SubjectDto subjectDto = subjectService.login(request);
		return subjectDto;
	}
	
	public void logout(HttpServletRequest request){
		
		subjectService.logout(request);
	}
	
	public boolean hasAllRoles(String... roleIdentifiers){
		
		return subjectService.hasAllRoles(roleIdentifiers);
	}
	
	public boolean[] hasRoles(String... roleIdentifiers){
		
		return subjectService.hasRoles(roleIdentifiers);
	}
	
	public boolean hasRole(String roleIdentifier){
	
		return subjectService.hasRole(roleIdentifier);
	}
	
	public boolean hasAllPermissions(String... permissions){
		
		return subjectService.hasAllPermissions(permissions);
	}
	
	public boolean[] hasPermissions(String... permissions){
		
		return subjectService.hasPermissions(permissions);
	}
	
	public boolean hasPermission(String permission){
		
		return subjectService.hasPermission(permission);
	}
	
	public void setSubjectByType(SecurityConfigType type){
		
		subjectService.setSubjectByType(type);
	}
}
