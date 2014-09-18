package com.cap.iit.app.security.service;

import java.util.Set;

public interface Authorizer {
	boolean[] hasRoles(Set<String> subjectRoles, String... roleIdentifiers);
	boolean hasAllRoles(Set<String> subjectRoles, String... roleIdentifiers);
	boolean[] hasPermissions(Set<String> subjectPermissions, String... permissions);
	boolean hasAllPermissions(Set<String> subjectPermissions, String... permissions);
}
