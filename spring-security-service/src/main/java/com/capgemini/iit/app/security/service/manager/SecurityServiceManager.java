package com.capgemini.iit.app.security.service.manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.iit.app.security.config.SecurityConfigType;
import com.capgemini.iit.app.security.subject.SubjectDelegate;
import com.capgemini.iit.app.security.subject.dto.SubjectDto;

public class SecurityServiceManager {
	
	private static final String APPLICATION_CONTEXT =  "service-beans.xml";	//"spring-security-ldap.xml"
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

	public void setSubjectService(SubjectDelegate subjectService) {
		this.subjectService = subjectService;
	}
	
	//TODO an argument can be placed here to indicate which subject to use
	//this could also delegate to a lookupservice to check which security config to use (spring or shiro)
	public static SecurityServiceManager getInstance(){
		ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
		SecurityServiceManager securityManager = (SecurityServiceManager) context.getBean("securityServiceManager");
		((AbstractApplicationContext) context).close(); 
		return securityManager;
	}
}
