package com.capgemini.iit.app.security.subject;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.capgemini.iit.app.security.config.SecurityConfigType;
import com.capgemini.iit.app.security.subject.dto.SubjectDto;

@Component
public class SubjectDelegate {
	@Autowired
	private Subject subject;	//this will be set in xml, either spring security or shiro can be used
	private SubjectLookup lookupService = new SubjectLookup();
	
	private Logger logger = Logger.getLogger(SubjectDelegate.class);
	
	public SubjectDto login(String username, String password){
		
		logger.info("creating authentication token..");
		//check for null values
		if (null == username || null == password || username.isEmpty() || password.isEmpty()) {
			logger.error("username and/or password is empty or null");
			throw new IllegalArgumentException("username and/or password is empty or null");
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		logger.info("authentication token successfully created");

		subject.createSession(token);
		Authentication authenticatedUser = subject.authenticate(token);
		
		SubjectDto subjectDto = null;
		if (null == authenticatedUser) {
			return null;
		} else {
			subjectDto =  subject.createSubjectDto(authenticatedUser);
			return subjectDto;
		}
		
	}
	
	public void logout(){
		
		subject.logout();
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
