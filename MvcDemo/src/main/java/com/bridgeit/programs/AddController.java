package com.bridgeit.programs;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.bridgeit.model.User;
import com.bridgeit.service.UserManager;

@Controller
public class AddController {

	UserManager userManager;
	
	 public AddController(UserManager userManager) {
		
		this.userManager = userManager;
	}
		
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("loginUser") User user,HttpServletRequest request,HttpServletResponse response ) 
	{
		
		
		ModelAndView modelAndView=new ModelAndView();
		
		System.out.println("email :"+user.getEmail());
		System.out.println("password "+user.getPassword());
		System.out.println("userManager "+userManager.findUser(user));
		if(userManager.findUser(user))
		{		
			HttpSession httpSession=request.getSession();

			httpSession.setAttribute("userObject", user);
			System.out.println("session id"+httpSession.getId());
			
			
			Cookie cookie=new Cookie("cookie", user.getEmail());
			response.addCookie(cookie);
			
			modelAndView.setViewName("display");
			modelAndView.addObject(user);
		}
		else
		{
			System.out.println("error");
			modelAndView.addObject("message","please enter valid email and password");
			modelAndView.setViewName("forward:index.jsp");
		}

		return modelAndView;
			 
	}
	
}
