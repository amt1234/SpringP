package com.bridgeit.programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.model.User;
import com.bridgeit.service.UserManager;

@Controller
public class RegisterController {
//	private UserDao userDao;
	@Autowired
	UserManager userManager;
/*
	public RegisterController(UserManager userManager) {
		this.userManager=userManager;
	}
*/
	@RequestMapping(value="/registerController",method=RequestMethod.POST)
	public String register(@ModelAttribute("registerUser") User user,Model model) {
		
		System.out.println(user.getUserid());
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getMobileNo());
		System.out.println(user.getDob());
		userManager.addUser(user);
		userManager.listAllUser();

		return "redirect:index.jsp";
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView rgisterMapping() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Register");
		return modelAndView;
	}

}
