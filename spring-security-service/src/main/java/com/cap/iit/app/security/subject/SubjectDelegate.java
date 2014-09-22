package com.cap.iit.app.security.subject;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cap.iit.app.security.config.SecurityConfigType;
import com.cap.iit.app.security.subject.dto.SubjectDto;

@Component
public class SubjectDelegate {
	@Autowired
	private Subject subject;	//spring is used by default and shiro subject is not yet implemented
	private SubjectLookup lookupService = new SubjectLookup();
	
	public SubjectDto login(HttpServletRequest request){
		
		Authentication authenticatedUser = subject.login(request);
		SubjectDto subjectDto = null;
		if (null == authenticatedUser) {
			return subjectDto;
		} else {
			subjectDto =  subject.setSubjectDto(authenticatedUser);
		}
		
		return subjectDto;
	}
	
	public void logout(HttpServletRequest request){
		
		subject.logout(request);
	}
	
	public boolean hasAllRoles(String... roleIdentifiers){
		
		Set<String> subjectRoles = subject.getSubjectRoles();
		return subject.hasAllRoles(subjectRoles, roleIdentifiers);
	}
	
	public boolean[] hasRoles(String... roleIdentifiers){
		
		Set<String> subjectRoles = subject.getSubjectRoles();
		return subject.hasRoles(subjectRoles, roleIdentifiers);
	}
	
	public boolean hasRole(String roleIdentifier){
		
		Set<String> subjectRoles = subject.getSubjectRoles();
		return subject.hasAllRoles(subjectRoles, roleIdentifier);
	}
	
	public boolean hasAllPermissions(String... permissions){
		
		Set<String> subjectPermissions = subject.getSubjectPermissions();
		return subject.hasAllPermissions(subjectPermissions, permissions);
	}
	
	public boolean[] hasPermissions(String... permissions){
		
		Set<String> subjectPermissions = subject.getSubjectPermissions();
		return subject.hasPermissions(subjectPermissions, permissions);
	}
	
	public boolean hasPermission(String permission){
		
		Set<String> subjectPermissions = subject.getSubjectPermissions();
		return subject.hasAllPermissions(subjectPermissions, permission);
	}
	
	public void setSubjectByType(SecurityConfigType type){
		
		this.subject = lookupService.getSubjectByType(type);
	}
}
