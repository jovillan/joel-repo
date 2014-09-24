package com.cap.iit.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class NoRedirectLogoutSuccessHandler implements LogoutSuccessHandler {
	
	private Logger logger = Logger.getLogger(NoRedirectLogoutSuccessHandler.class);
	
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // no redirect !! (unlike @SimpleUrlLogoutSuccessHandler, that redirects after logout)
    	logger.info("no redirect logout success handler");
    }
}