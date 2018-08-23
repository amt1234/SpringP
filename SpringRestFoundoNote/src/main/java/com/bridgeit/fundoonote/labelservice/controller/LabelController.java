package com.bridgeit.fundoonote.labelservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonote.labelservice.model.Label;
import com.bridgeit.fundoonote.labelservice.model.LabelDto;
import com.bridgeit.fundoonote.labelservice.service.ILableService;

@RestController
@RequestMapping(value = "/label")
public class LabelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LabelController.class);

	@Autowired
	ILableService lableService;

	@PostMapping(value = "/create")
	public ResponseEntity<String> createLabel(@RequestBody LabelDto labelDto, HttpServletRequest request) {
		LOGGER.info("CREATE LABLE");

		String token = request.getHeader("userid");
		if (lableService.createLable(labelDto, token))
			return new ResponseEntity<String>("Lable created", HttpStatus.ACCEPTED);
		return new ResponseEntity<String>("Lable already created", HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/update")
	public ResponseEntity<?> updateLabel(@RequestBody Label label, HttpServletRequest request) {
		LOGGER.info("Update LABLE");

		String token = request.getHeader("userid");
		if (lableService.updateLabels(token, label))
			return new ResponseEntity<String>("Label update", HttpStatus.OK);
		return new ResponseEntity<String>("Label not update", HttpStatus.CONFLICT);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<?> retriveAllLabels(HttpServletRequest request) {
		LOGGER.info("List of Label");

		String token = request.getHeader("userid");
		List<LabelDto> list = lableService.listOfAllLabels(token);
		if (list != null)
			return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteLabel(@PathVariable("id") long id, HttpServletRequest request) {
		LOGGER.info("Delete  Label");

		String token = request.getHeader("userid");
		if (lableService.deleteLabels(token, id))
			return new ResponseEntity<String>("label delete", HttpStatus.OK);
		return new ResponseEntity<String>("label not delete", HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/addLabel/{id}")
	public ResponseEntity<?> addLabel(@PathVariable("id") long id, @RequestBody LabelDto labelDto) {
		if (lableService.addLabels(id, labelDto))
			return new ResponseEntity<String>("label add on note", HttpStatus.OK);
		return new ResponseEntity<String>("label not add on note", HttpStatus.CONFLICT);
	}
	
	@PostMapping(value="/removeLabel/{id}")
	public ResponseEntity<?> removeLabel(@PathVariable("id") long id, @RequestBody LabelDto labelDto){
		if(lableService.removeLables(id, labelDto))
			return new ResponseEntity<String>("label remove on note",HttpStatus.OK);
		return new ResponseEntity<String>("label not remove on note",HttpStatus.CONFLICT);	
	}
}
