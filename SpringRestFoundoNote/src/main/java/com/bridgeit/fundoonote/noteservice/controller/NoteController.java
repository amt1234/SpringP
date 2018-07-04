package com.bridgeit.fundoonote.noteservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.service.INoteService;

@RestController
@RequestMapping(value="/note")
public class NoteController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	 INoteService iNoteService;

	@PostMapping(value = "/create")
	public ResponseEntity<String> createNote(@RequestBody Note note,HttpServletRequest request) {
		LOGGER.info("CREATE NOTE");
		long id=request.getDateHeader("userid");
		if (iNoteService.createUserNote(note,id))
			return new ResponseEntity<String>("note created", HttpStatus.CREATED);
		return new ResponseEntity<String>("note not created", HttpStatus.CONFLICT);

	}
}
