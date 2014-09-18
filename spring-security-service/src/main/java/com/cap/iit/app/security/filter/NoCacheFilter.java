package com.cap.iit.app.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.DelegatingFilterProxy;

/*this is currently not used,
 * although it is directly coded in all the controller methods at the client project*/
public class NoCacheFilter extends DelegatingFilterProxy {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
	    HttpServletResponse hsr = (HttpServletResponse) res;
	    hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    hsr.setDateHeader("Expires", 0); // Proxies.
	    chain.doFilter(req, res);
//	    super.doFilter(req, res, chain);
	}
}
