package com.bridgeit.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.model.User;
import com.bridgeit.service.UserServiceManager;

@Controller
public class LoginController {

	// service interface
	UserServiceManager userServiceManager;

	public LoginController(UserServiceManager userManager) {

		this.userServiceManager = userManager;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@ModelAttribute("loginUser") User user, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();

		List<User> list = (List<User>) userServiceManager.findUser(user);
		String email2 = null;
		String password2 = null;
		for (User user2 : list) {
			email2 = user2.getEmail();
			password2 = user2.getPassword();
		}

		if ((email2.equals(user.getEmail()) && (password2.equals(user.getPassword())))) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("userObject", user);
			System.out.println("valid user");
			System.out.println("session id" + httpSession.getId());

			Cookie cookie = new Cookie("cookie", user.getEmail());
			response.addCookie(cookie);

			modelAndView.addObject(user);
			return "redirect:loadData";
		} else {

			modelAndView.addObject("message", "please enter valid email and password");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/loadData", method = RequestMethod.GET)
	public ModelAndView dataLoad(HttpSession httpsession) {
		ModelAndView modelAndView = new ModelAndView();
		List<User> list = (List<User>) userServiceManager.findUser((User) httpsession.getAttribute("userObject"));
		
		for (User user2 : list) {
			modelAndView.addObject(user2.getEmail());
			modelAndView.addObject(user2.getPassword());
			modelAndView.addObject(user2.getUsername());
		}
		modelAndView.setViewName("display");
		return modelAndView;
	}
}
