package com.bridgeit.fundoonote.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonote.jwt.Sender;
import com.bridgeit.fundoonote.model.LoginDTO;
import com.bridgeit.fundoonote.model.RegistrationDTO;
import com.bridgeit.fundoonote.services.UserService;


@RestController
@RequestMapping(value="/user")
public class RegisterController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	Sender sender;
	
	@PostMapping(value = "/save")
	public ResponseEntity<?> userRegistration(@Validated @RequestBody RegistrationDTO registrationDTO, BindingResult bindingResult) {
		LOGGER.info("START CREATE NEW EMPLOYEE");
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		if (userService.registationSave(registrationDTO))
			return new ResponseEntity<String>("Registration sucessfully done", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Already existing user", HttpStatus.CONFLICT);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> userLogin(@Validated @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
		LOGGER.info("check valid user");
		System.out.println("password check in controller "+loginDTO.getPassword()+" "+loginDTO.getUserEmail());
		if (userService.check(loginDTO.getUserEmail(),loginDTO.getPassword()))
			return new ResponseEntity<String>("Login sucessfully done", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<String>("Not valid user", HttpStatus.CONFLICT);
	}
	
	
	@GetMapping(value="/verifyAccount/{token:.+}")
	public ResponseEntity<?> isUserActive(@PathVariable("token") String token)
	{
		if(userService.getUserTokenVerify(token))
		return new ResponseEntity<String>("verified user",HttpStatus.OK);
		else
		return new ResponseEntity<String>("not verified user",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping(value="/forgotpassword/{emailId}")
	public ResponseEntity<String> forgotPassword(@PathVariable("emailId") String token,String emailId,RegistrationDTO registrationDTO)
	{
		//if(userService.getUserTokenVerify(token, isActiveUser))
		if(userService.forgotUserPassword(registrationDTO, emailId,token))
		return new ResponseEntity<String>("reset password",HttpStatus.OK);
		else
		return new ResponseEntity<String>("Invalid user",HttpStatus.CONFLICT);
		
	}
	
	/*@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int empId)
	{
		LOGGER.info("Start getEmployee. ID="+empId);
		
		//Employee employee=employeedao.getEmployeeid(empId);
		if(employee==null)
		{
			return new ResponseEntity<String>("No Customer found for ID " + empId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);	
	}*/

}