package com.bridgeit.fundoonote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

	@PostMapping(value = "/user/save")
	public ResponseEntity<?> registerUse(@Validated @RequestBody User user, BindingResult bindingResult) {
		LOGGER.info("START CREATE NEW EMPLOYEE");
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		System.out.println("hello save");
		if (userService.save(user) != null)
			return new ResponseEntity<String>("Registration sucessfully done", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Already existing user", HttpStatus.CONFLICT);

	}
	
	@PostMapping(value = "/user/checkValidUser")
	public ResponseEntity<String> checkUser(@Validated @RequestBody User user, BindingResult bindingResult) {
		LOGGER.info("check valid user");
		System.out.println("password check in controller "+user.getPassword()+" "+user.getUserEmail());
		if (userService.check(user.getUserEmail(),user.getPassword()))
			return new ResponseEntity<String>("Login sucessfully done", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<String>("Not valid user", HttpStatus.CONFLICT);

	}

}
