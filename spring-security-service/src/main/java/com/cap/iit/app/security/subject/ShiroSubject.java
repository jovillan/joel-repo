package com.cap.iit.app.security.subject;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.cap.iit.app.security.subject.dto.SubjectDto;

public class ShiroSubject implements Subject {

	@Override
	public Authentication login(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean[] hasRoles(Set<String> subjectRoles,
			String... roleIdentifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAllRoles(Set<String> subjectRoles,
			String... roleIdentifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAllPermissions(Set<String> subjectPermissions,
			String... permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getSubjectRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSubjectPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectDto setSubjectDto(Authentication authenticatedUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] hasPermissions(Set<String> subjectPermissions,
			String... permissions) {
		// TODO Auto-generated method stub
		return null;
	}

}
