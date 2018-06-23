package com.bridgeit.fundoonote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonote.model.User;
import com.bridgeit.fundoonote.services.UserService;

@RestController
public class RegisterController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	UserService userService;
	@GetMapping("/")
	public String getMethod() {
		LOGGER.info("START CREATE NEW EMPLOYEE");
		System.out.println("abc");
		return "home";
	}
	
	@PostMapping(value="/user/save")
	public ResponseEntity<User>registerUse(@RequestBody User user)
	{
		LOGGER.info("START CREATE NEW EMPLOYEE");
		
		System.out.println("hello save");
		userService.save(user);
		System.out.println("userid"+user.getUserId());
		return new ResponseEntity<User>(user,HttpStatus.OK);	
	}
	
	
	
}
