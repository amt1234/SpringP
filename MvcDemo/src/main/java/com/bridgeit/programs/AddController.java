package com.bridgeit.programs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {

	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response )
	{
		String username=request.getParameter("email");
		String password=request.getParameter("pass");
		
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("display");
		modelAndView.addObject("emailid", username);
		modelAndView.addObject("passwd", password);
		
		return modelAndView;
			 
	}
	
}