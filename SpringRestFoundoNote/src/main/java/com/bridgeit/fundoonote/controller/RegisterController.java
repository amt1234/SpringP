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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonote.jwt.Sender;
import com.bridgeit.fundoonote.model.EmailInfo;
import com.bridgeit.fundoonote.model.LoginDTO;
import com.bridgeit.fundoonote.model.RegistrationDTO;
import com.bridgeit.fundoonote.services.UserService;


@RestController
@RequestMapping(value="/user")
public class RegisterController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	private static final int String = 0;

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
	
	
	@GetMapping(value="/verifyAccount/{token}")
	public ResponseEntity<?> userVerification(@PathVariable("token") String token)
	{
		//String value = syncommands.get(key);
		if(userService.getUserTokenVerify(token))
		return new ResponseEntity<String>("verified user",HttpStatus.OK);
		else
		return new ResponseEntity<String>("not verified user",HttpStatus.NOT_FOUND);
		
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