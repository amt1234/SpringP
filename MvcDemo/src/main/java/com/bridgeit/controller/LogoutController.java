package com.bridgeit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession httpSession=request.getSession();
		httpSession.setMaxInactiveInterval(10);
		httpSession.invalidate();
		
		return "login";
		
	}

}
