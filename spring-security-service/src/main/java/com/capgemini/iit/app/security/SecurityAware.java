package com.capgemini.iit.app.security;

import com.capgemini.iit.app.security.service.manager.SecurityServiceManager;

public interface SecurityAware {
	SecurityServiceManager setSecurityManager(SecurityServiceManager securityManager);
}
