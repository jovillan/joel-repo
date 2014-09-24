package com.capgemini.iit.app.security.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.iit.app.security.config.SecurityConfigType;
import com.capgemini.iit.app.security.subject.SubjectDelegate;
import com.capgemini.iit.app.security.subject.dto.SubjectDto;

@Component
public class CapSecurityManager {
	@Autowired
	private SubjectDelegate subjectService;
	
	public SubjectDto login(String username, String password) {
		
		SubjectDto subjectDto = subjectService.login(username, password);
		return subjectDto;
	}
	
	public void logout(){
		
		subjectService.logout();
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
