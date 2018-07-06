package com.bridgeit.fundoonote.noteservice.controller;

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

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.service.INoteService;

@RestController
@RequestMapping(value = "/note")
public class NoteController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	INoteService iNoteService;

	@PostMapping(value = "/create")
	public ResponseEntity<?> createNote(@RequestBody Note note, HttpServletRequest request) {
		LOGGER.info("CREATE NOTE");
		String token = request.getHeader("userid");
		if ((note = iNoteService.createUserNote(note, token)) != null)
			return new ResponseEntity<Note>(note, HttpStatus.CREATED);
		return new ResponseEntity<String>("note not created", HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/update")
	public ResponseEntity<String> updateNote(@RequestBody Note note, HttpServletRequest request) {
		LOGGER.info("UPDATE NOTE");
		String token = request.getHeader("userid");
		if (iNoteService.updateUserNote(note, token))
			return new ResponseEntity<String>("note updated", HttpStatus.OK);
		return new ResponseEntity<String>("note not updated", HttpStatus.CONFLICT);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<Note>> retriveAllUser(HttpServletRequest request) {
		LOGGER.info("LIST OF USER'S NOTES");
		String token = request.getHeader("userid");
		List<Note> list = iNoteService.listOfNote(token);
		if (list != null)
			return new ResponseEntity<List<Note>>(list, HttpStatus.OK);
		return new ResponseEntity<List<Note>>(HttpStatus.CONFLICT);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteNote(@PathVariable("id") long id,HttpServletRequest request) {
		LOGGER.info("DELETE USER NOTE");
		String token = request.getHeader("userid");
		if (iNoteService.deleteUserNote(token, id))
			return new ResponseEntity<String>("Note deleted sucessfully", HttpStatus.OK);
		return new ResponseEntity<String>("Note not deleted", HttpStatus.CONFLICT);

	}
}
