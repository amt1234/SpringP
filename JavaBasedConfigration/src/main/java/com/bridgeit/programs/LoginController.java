package com.bridgeit.programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bridgeit.model.User;
import com.bridgeit.service.IUserService;

@Controller
public class LoginController {
/*	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "/greeting";
	}*/
	@Autowired
	IUserService userService;
	
	    @RequestMapping(value="/greeting",method=RequestMethod.POST)    
	    public String greet(@ModelAttribute("loginUser") User user){
	    	
	       /* String greet =" Hello !!!" + name + " How are You?";
	        model.addAttribute("greet", greet);
	        System.out.println(greet);*/
	    	
	    	String greet="hello world !!";
	    	System.out.println(greet);
	    	System.out.println(user.getUserid());
			System.out.println(user.getUsername());
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			System.out.println(user.getMobileNo());
			System.out.println(user.getDob());
	       userService.addUser(user);
	       System.out.println("done.....");
	      return "showMessage";
	    }
}
