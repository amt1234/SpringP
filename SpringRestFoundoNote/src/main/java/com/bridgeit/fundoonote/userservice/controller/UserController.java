package com.bridgeit.fundoonote.userservice.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonote.userservice.jwt.Sender;
import com.bridgeit.fundoonote.userservice.model.EmailInfo;
import com.bridgeit.fundoonote.userservice.model.LoginDTO;
import com.bridgeit.fundoonote.userservice.model.RegistrationDTO;
import com.bridgeit.fundoonote.userservice.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	Sender sender;

	@PostMapping(value = "/save")
	public ResponseEntity<?> userRegistration(@Validated @RequestBody RegistrationDTO registrationDTO,
			BindingResult bindingResult, HttpServletRequest request) {
		LOGGER.info("START CREATE NEW EMPLOYEE");
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		
		String url = request.getRequestURL().toString();
		String link = url.substring(0, url.lastIndexOf("/")).concat("/verifyaccount/");
		
		if (userService.registationSave(registrationDTO, link))
			return new ResponseEntity<String>("Registration sucessfully done", HttpStatus.OK);
		return new ResponseEntity<String>("Already existing user", HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> userLogin(@Validated @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
		LOGGER.info("check valid user");
		String token;
		if ((token=userService.check(loginDTO.getUserEmail(), loginDTO.getPassword()))!=null )
			
			return new ResponseEntity<String>("Login sucessfully done "+token, HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("Not valid user", HttpStatus.CONFLICT);
	}

	@GetMapping(value = "/verifyaccount/{token:.+}")
	public ResponseEntity<?> isUserActive(@PathVariable("token") String token) {
		if (userService.getUserTokenVerify(token))
			return new ResponseEntity<String>("verified user", HttpStatus.OK);
		return new ResponseEntity<String>("not verified user", HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/forgotpassword")
	public ResponseEntity<String> forgotPassword(@RequestBody EmailInfo emailInfo, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String link = url.substring(0, url.lastIndexOf("/")).concat("/resetpassword/");
		if (userService.resetUserPassword(emailInfo, link))
			return new ResponseEntity<String>("Link send to reset password", HttpStatus.OK);
		return new ResponseEntity<String>("Invalid user", HttpStatus.OK);
	}

	@PostMapping(value = "/resetpassword/{token:.+}")
	public ResponseEntity<?> resetPasswordMethod(@PathVariable("token") String token,
			@RequestBody Map<String, String> password) {
		if (userService.forgotUserPassword(token, password.get("password")))
			return new ResponseEntity<String>("reset password sucessfully", HttpStatus.OK);
		return new ResponseEntity<String>("Invalid user", HttpStatus.CONFLICT);
	}

}