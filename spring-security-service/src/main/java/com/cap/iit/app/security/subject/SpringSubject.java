package com.cap.iit.app.security.subject;

import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Service;

import com.cap.iit.app.security.subject.dto.SubjectDto;

@Service
public class SpringSubject implements Subject {

	private static final Logger logger = Logger.getLogger(SpringSubject.class);

	@Autowired
	protected RequestCache requestCache;

	@Autowired
	@Qualifier("authenticationManager")
	protected AuthenticationManager authenticationManager;

	private SubjectDto subjectDto;
	private boolean isAuthenticated;

	@Override
	public Authentication login(HttpServletRequest request) {
		logger.info("creating authentication token..");
		logSessionDetails(request);

		//		String username = request.getParameter("username").toLowerCase();	//for case insensitive login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//check for null values
		if (null == username || null == password || username.isEmpty() || password.isEmpty()) {
			logger.error("username and/or password is empty or null");
			return null;
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		logger.info("authentication token successfully created");

		// generate session if one doesn't exist
		logger.info("creating http session..");
		request.getSession();
		token.setDetails(new WebAuthenticationDetails(request));
		logger.info("http session successfully created");

		logger.info("authenticating user..");
		Authentication authenticatedUser = null;
		try{
			authenticatedUser = authenticationManager.authenticate(token);
		} catch (BadCredentialsException bce) {
			logger.error("incorrect credentials");
			return null;
		} catch (AccountStatusException ase) {
			logger.error("acct is either disabled or locked");
			return null;
		} catch (Exception e) {
			logger.error("an error occurred during authentication");
			return null;
		}

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		isAuthenticated = true;
		logSessionDetails(request);
		logger.info("user successfully authenticated");
		return authenticatedUser;
	}

	@Override
	public SubjectDto setSubjectDto(Authentication authenticatedUser) {
		logger.info("creating subjectDto..");
		Object principal = authenticatedUser.getPrincipal();
		if (principal instanceof LdapUserDetailsImpl) {
			LdapUserDetailsImpl ldapUserDetails = (LdapUserDetailsImpl)principal;
			subjectDto = new SubjectDto();
			subjectDto.setPrincipal(ldapUserDetails.toString());
			subjectDto.setDn(ldapUserDetails.getDn());
			subjectDto.setUsername(ldapUserDetails.getUsername());
			subjectDto.setRoles(AuthorityUtils.authorityListToSet(ldapUserDetails.getAuthorities()));
			logger.info("subjectDto created");
		} else {
			logger.error("principal is not an instance of LdapUserDetailsImpl: subjectDto is null");
		}
		return subjectDto;
	}

	@Override
	public void logout(HttpServletRequest request) {
		logger.info("logging out..");
		logger.info("logout session attributes before logout: ");
		logSessionDetails(request);

		SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler(); 

		//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ctxLogOut.logout(request, null, null);	//accdg to spring docs response and auth are not used
		SecurityContextHolder.getContext().setAuthentication(null);
		request.getSession().invalidate();
		isAuthenticated = false;

		logger.info("logout session attributes after logout: ");
		logSessionDetails(request);
		logger.info("user successfully logged out");
	}

	@Override
	public Set<String> getSubjectRoles() {

		if (isAuthenticated) {
			return subjectDto.getRoles();
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean hasAllRoles(Set<String> subjectRoles,
			String... roleIdentifiers) {

		roleIdLoop:
			for (String roleId : roleIdentifiers) {
				for (String subjectRole : subjectRoles) {
					if (roleId.equalsIgnoreCase(subjectRole)) {		//roles are not case sensitive
						continue roleIdLoop;
					}
				}
				return false;
			}

		return true;
	}

	@Override
	public boolean[] hasRoles(Set<String> subjectRoles,
			String... roleIdentifiers) {

		boolean hasRoles[] = new boolean[roleIdentifiers.length];

		roleIdLoop:
			for (int i = 0; i < hasRoles.length; i++) {
				for (String subjectRole : subjectRoles) {
					if (roleIdentifiers[i].equalsIgnoreCase(subjectRole)) {
						hasRoles[i] = true;
						continue roleIdLoop;
					}
				}
				hasRoles[i] = false;
			}

		return hasRoles;
	}

	@Override
	public Set<String> getSubjectPermissions() {

		if (isAuthenticated) {
			return subjectDto.getPermissions();
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean hasAllPermissions(Set<String> subjectPermissions,
			String... permissions) {

		permissionsLoop:
			for (String permission : permissions) {
				for (String subjectPermission : subjectPermissions) {
					if (permission.equalsIgnoreCase(subjectPermission)) {	//permissions are not case sensitive
						continue permissionsLoop;
					}
				}
				return false;
			}

		return true;
	}

	@Override
	public boolean[] hasPermissions(Set<String> subjectPermissions,
			String... permissions) {

		boolean hasPermissions[] = new boolean[permissions.length];

		permissionLoop:
			for (int i = 0; i < hasPermissions.length; i++) {
				for (String subjectPermission : subjectPermissions) {
					if (permissions[i].equalsIgnoreCase(subjectPermission)) {
						hasPermissions[i] = true;
						continue permissionLoop;
					}
				}
				hasPermissions[i] = false;
			}

		return hasPermissions;
	}

	@SuppressWarnings("rawtypes")
	private void logSessionDetails(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration attribs = session.getAttributeNames();
		while (attribs.hasMoreElements()){
			Object attrib = attribs.nextElement();
			logger.info("Session element name: " + attrib);
			Object obj = session.getAttribute((String) attrib);
			logger.info("Session element " + obj);
		}
	}

}
