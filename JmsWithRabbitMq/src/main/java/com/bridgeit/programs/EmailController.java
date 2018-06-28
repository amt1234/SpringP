package com.bridgeit.programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmailController {
	
	@Autowired
	Runner runner;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public void getMethod()
	{
		System.out.println("starting controller");
		
	}
	
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public String sendEmail() throws Exception {
		runner.runMethod();
		return "showMessage";
	}
}
