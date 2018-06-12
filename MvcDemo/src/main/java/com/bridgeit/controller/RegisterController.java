package com.bridgeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.model.User;
import com.bridgeit.service.UserServiceManager;

@Controller
public class RegisterController {

	@Autowired
	UserServiceManager userManager;

	@RequestMapping(value = "/registerCompleted", method = RequestMethod.POST)
	public String register(@ModelAttribute("registerUser") User user) {

		System.out.println(user.getUserid());
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getMobileNo());
		System.out.println(user.getDob());

		userManager.addUser(user);
		
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView rgisterMapping() {

		return new ModelAndView("Register");
	}
}
