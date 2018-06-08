package com.bridgeit.programs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView modelAndView)
	{
		modelAndView.setViewName("index");
		return modelAndView;
		
	}
	/*@RequestMapping(value="/login",RequestMethod.GET)
	public ModelAndView setMethod()
	{
		return null;
		
	}*/
}
