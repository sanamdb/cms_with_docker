package com.learning.college.GatewayService.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	@RequestMapping("/secure")
	public String secure(HttpSession session) {
		return "secure_"+session.getId();
	}
	
//	@RequestMapping("/secure")
//	public CsrfToken secure(HttpServletRequest request) {
//		return (CsrfToken) request.getAttribute("_csrf");
//	}
	

}
