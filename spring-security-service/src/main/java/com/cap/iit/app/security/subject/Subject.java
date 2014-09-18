package com.cap.iit.app.security.subject;

import java.util.Set;

import org.springframework.security.core.Authentication;

import com.cap.iit.app.security.service.Authenticator;
import com.cap.iit.app.security.service.Authorizer;
import com.cap.iit.app.security.subject.dto.SubjectDto;

public interface Subject extends Authenticator, Authorizer {
	Set<String> getSubjectRoles();
	Set<String> getSubjectPermissions();
//	SubjectDto setSubjectDto();
	SubjectDto setSubjectDto(Authentication authenticatedUser);
}
