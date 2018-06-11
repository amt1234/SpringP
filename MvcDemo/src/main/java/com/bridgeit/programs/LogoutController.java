package com.bridgeit.programs;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		httpSession.setMaxInactiveInterval(10);
		httpSession.invalidate();
		modelAndView.setViewName("index");
		return modelAndView;
		
	}

}
