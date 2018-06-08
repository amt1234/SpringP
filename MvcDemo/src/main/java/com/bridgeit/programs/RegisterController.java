package com.bridgeit.programs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.Dao.UserDao;
import com.bridgeit.Dao.UserDaoImpl;

@Controller
public class RegisterController {
	private UserDao userDao;
	
	@RequestMapping("/register")
public ModelAndView register(ModelAndView modelAndView)
{
	
	modelAndView.setViewName("Register");
	return modelAndView;
	
}
	
	
}
