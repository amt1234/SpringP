package com.bridgeit.fundoonote.noteservice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.model.WebScrap;
import com.bridgeit.fundoonote.noteservice.service.INoteService;
import com.bridgeit.fundoonote.userservice.model.Response;

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
			return new ResponseEntity<>(new Response("201", note), HttpStatus.CREATED);
		return new ResponseEntity<>(new Response("406", "note not created"), HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/update")
	public ResponseEntity<?> updateNote(@RequestBody Note note, HttpServletRequest request) {
		LOGGER.info("UPDATE NOTE");
		String token = request.getHeader("userid");
		if (iNoteService.updateUserNote(note, token))
			return new ResponseEntity<>(new Response("200", "note updated"), HttpStatus.OK);
		return new ResponseEntity<>(new Response("406", "note not updated"), HttpStatus.CONFLICT);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<?> retriveAllUser(HttpServletRequest request) {
		LOGGER.info("LIST OF USER'S NOTES");
		String token = request.getHeader("userid");
		List<Note> list = iNoteService.listOfNote(token);
		if (list != null)
			return new ResponseEntity<>(new Response("200", list), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable("id") long id, HttpServletRequest request) {
		LOGGER.info("DELETE USER NOTE");
		String token = request.getHeader("userid");
		if (iNoteService.deleteUserNote(token, id))
			return new ResponseEntity<>(new Response("200", "Note deleted sucessfully"), HttpStatus.OK);
		return new ResponseEntity<>(new Response("406", "Note not deleted"), HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/uploadFile")
	public ResponseEntity<?> uploadMultipleFile(@RequestParam("file") MultipartFile file) {
		LOGGER.info("uploadFile");
		String image;
		if ((image = iNoteService.uploadFile(file)) != null)
			return new ResponseEntity<>(new Response("200", image), HttpStatus.OK);
		return new ResponseEntity<>(new Response("404", "You failed to upload"), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/loadFile/{filename:.+}")
	public ResponseEntity<?> getFile(@PathVariable("filename") String filename, HttpServletRequest httpServletRequest)
			throws IOException {
		LOGGER.info("loadFile");
		Resource file = iNoteService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping(value = "/getCollaboratedNotes")
	public ResponseEntity<?> getCollaboratedNotes(HttpServletRequest request) {
		LOGGER.info(" Collaborator list ");
		String token = request.getHeader("userid");
		List<Note> list = iNoteService.getCollaboratedNotes(token);
		if (list != null)
			return new ResponseEntity<>(new Response("200", list), HttpStatus.OK);
		return new ResponseEntity<>(new Response("404", "No collaborated note"), HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/removeWebScrap/{id}")
	public ResponseEntity<?> removeWebScrap(@PathVariable("id") long noteId, @RequestBody WebScrap webScrap) {
		if (iNoteService.removeWebScrap(noteId, webScrap))
			return new ResponseEntity<>(new Response("200", "remove link from note"), HttpStatus.OK);
		return new ResponseEntity<>(new Response("404", "not remove note"), HttpStatus.CONFLICT);
	}
}
