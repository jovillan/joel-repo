package com.capgemini.iit.app.security.subject;

import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.capgemini.iit.app.security.subject.dto.SubjectDto;

public class ShiroSubject implements Subject {

	@Override
	public void createSession(UsernamePasswordAuthenticationToken token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Authentication authenticate(UsernamePasswordAuthenticationToken token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout() {
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
	public SubjectDto createSubjectDto(Authentication authenticatedUser) {
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
