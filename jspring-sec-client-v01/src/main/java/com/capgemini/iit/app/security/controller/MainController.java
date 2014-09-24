package com.capgemini.iit.app.security.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capgemini.iit.app.security.service.manager.CapSecurityManager;
import com.capgemini.iit.app.security.subject.dto.SubjectDto;

@Controller
@RequestMapping("/client")
public class MainController {

	@Autowired
	private CapSecurityManager capSecurityManager;

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public ModelAndView loginpage(ModelAndView model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout
	) {
		
		if (error != null) {
			System.out.println("error" + error);
			model.addObject("error", "Invalid username and password!");
		} else if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
	    
		model.setViewName("loginpage");
		return model;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ModelAndView authentication(ModelAndView model, 
			HttpServletRequest request, 
			HttpServletResponse response,
			RedirectAttributes redirectAttributes
	) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SubjectDto subjectDto = capSecurityManager.login(username, password);
		
		if (subjectDto == null) {
			System.out.println("redirect:/client/loginpage?error");
			model.setViewName("redirect:/client/loginpage?error");
		} else {
			displaySubjectDto(subjectDto);	//for testing purposes only
			
			System.out.println("redirect:/client/home");
			model.setViewName("redirect:/client/home");
			redirectAttributes.addAttribute("username", request.getParameter("username"));
		}
		
		return model;
	}

	private void displaySubjectDto(SubjectDto subjectDto) {
		System.out.println("**********Subject Dto**********");
		System.out.println(subjectDto.getPrincipal());
		System.out.println(subjectDto.getDn());
		System.out.println(subjectDto.getUsername());
		System.out.println(subjectDto.getRoles());
		System.out.println("**********End of Subject Dto**********");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(ModelAndView model, 
			HttpServletRequest request, 
			HttpServletResponse response
	) {
		capSecurityManager.logout();	//accdg to spring docs authentication and response is not used by SecurityContextLogoutHandler in logging out
		
		System.out.println("redirect:/client/loginpage?logout");
		model.setViewName("redirect:/client/loginpage?logout");
		return model;
	}

	/*test pages*/
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model, HttpServletResponse response, HttpServletRequest request) {

		model.addObject("visitor", "hello " + request.getParameter("username"));
		model.addObject("loginStatus", "successful");
		model.setViewName("home");
		
		displaySessionDetails("home session details: ", request);
		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(ModelAndView model, HttpServletResponse response, HttpServletRequest request) {
		
		model.setViewName("admin");
		displaySessionDetails("admin", request);
		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(ModelAndView model, HttpServletResponse response, HttpServletRequest request) {
		
		model.setViewName("user");
		displaySessionDetails("user", request);
		return model;
	}
	
	@SuppressWarnings("rawtypes")
	private void displaySessionDetails(String msg, HttpServletRequest request) {
		System.out.println(msg);
		HttpSession session = request.getSession();
		Enumeration attribs = session.getAttributeNames();
	    while (attribs.hasMoreElements()){
	    	Object attrib = attribs.nextElement();
	    	System.out.println("Main Session elements: " + attrib);
	    	Object obj = session.getAttribute((String) attrib);
	    	System.out.println("Main: " + obj);
	    }
	}

}