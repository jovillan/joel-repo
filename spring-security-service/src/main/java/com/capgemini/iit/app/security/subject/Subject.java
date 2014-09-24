package com.capgemini.iit.app.security.subject;

import java.util.Set;

import org.springframework.security.core.Authentication;

import com.capgemini.iit.app.security.service.Authenticator;
import com.capgemini.iit.app.security.service.Authorizer;
import com.capgemini.iit.app.security.subject.dto.SubjectDto;

public interface Subject extends Authenticator, Authorizer {
	Set<String> getSubjectRoles();
	Set<String> getSubjectPermissions();
	SubjectDto createSubjectDto(Authentication authenticatedUser);
}
