package com.capgemini.iit.app.security.subject.dto;

import java.util.Set;

public class SubjectDto {
	private String principal;			//all user details
	private String username;
	private String dn;					//ldap distinguished name
	private Set<String> permissions;	//for permission-based login
	private Set<String> roles;			//user groups
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public Set<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
}
