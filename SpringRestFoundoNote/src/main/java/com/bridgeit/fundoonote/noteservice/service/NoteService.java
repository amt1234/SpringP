package com.bridgeit.fundoonote.noteservice.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgeit.fundoonote.noteservice.controller.NoteController;
import com.bridgeit.fundoonote.noteservice.dao.INoteDao;
import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.model.ResponseDto;
import com.bridgeit.fundoonote.userservice.dao.UserDao;
import com.bridgeit.fundoonote.userservice.model.User;
import com.bridgeit.fundoonote.userservice.utility.IJwtProgram;

@Service
@Transactional
public class NoteService implements INoteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);
	@Autowired
	private INoteDao noteDao;

	@Autowired
	UserDao userDao;

	@Autowired
	IJwtProgram jwtToken;

	@Override
	public Note createUserNote(Note note, String token) {

		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		note.setUser(user);

		// getting noteId
		long noteid = noteDao.create(note);
		note = noteDao.checkNoteId(noteid);
		return note;
	}

	@Override
	@Transactional
	public boolean updateUserNote(Note note, String token) {

		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		// getting userid from note table
		Note oldNote = noteDao.checkNoteId(note.getNoteId());

		if ((oldNote.getUser().getUserId()) == (user.getUserId())) {
			oldNote.setNoteId(note.getNoteId());
			oldNote.setNoteTitle(note.getNoteTitle());
			oldNote.setNoteDescribtion(note.getNoteDescribtion());
			oldNote.setColor(note.getColor());
			oldNote.setReminderDate(note.getReminderDate());
			oldNote.setReminderTime(note.getReminderTime());
			oldNote.setUpdatedDate(note.getUpdatedDate());
			oldNote.setNoteArchiev(note.isNoteArchiev());
			oldNote.setNotePinned(note.isNotePinned());
			oldNote.setNoteTrash(note.isNoteTrash());
			noteDao.updateNote(oldNote);
			return true;
		} else
			return false;
	}

	@Override
	public List<Note> listOfNote(String token) {

		List<ResponseDto> listOfResponseDto = new ArrayList<>();
		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		// passing user as parameter because in pojo there is user field
		List<Note> listOfNote = noteDao.notesList(user);

		// for (Note note : listOfNote) {
		// ResponseDto newNote=new ResponseDto();
		// newNote.setNoteId(note.getNoteId());
		// newNote.setNoteTitle(note.getNoteTitle());
		// newNote.setNoteDescribtion(note.getNoteDescribtion());
		// newNote.setNoteArchiev(note.isNoteArchiev());
		// newNote.setNotePinned(note.isNotePinned());
		// newNote.setNoteTrash(note.isNoteTrash());
		// newNote.setCreatedDate(note.getCreatedDate());
		// newNote.setUpdatedDate(note.getUpdatedDate());
		// newNote.setReminderDate(note.getReminderDate());
		// newNote.setReminderTime(note.getReminderTime());
		// newNote.setColor(note.getColor());
		//
		// listOfResponseDto.add(newNote);
		// }
		//
		// return listOfResponseDto;

		return listOfNote;
	}

	@Override
	public boolean deleteUserNote(String token, long noteid) {
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		Note note = noteDao.checkNoteId(noteid);
		if ((note.getUser().getUserId()) == (user.getUserId())) {
			if (noteDao.deleteNoteOfUser(noteid))
				return true;
		}
		return false;
	}
	
	String imagePath="/home/bridgelabz/Documents/workspace-sts-3.9.4.RELEASE/SpringRestFoundoNote/src/main/java/com/bridgeit/fundoonote/profile";
	private final Path rootPath=Paths.get(imagePath);
	
	@Override
	public String uploadFile(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				File dir = new File(imagePath);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				LOGGER.info("Server File Location=" + serverFile.getAbsolutePath());

				return "http://localhost:8080/SpringRestFoundoNote/note/loadFile/" + file.getOriginalFilename();
			} catch (Exception e) {
				return "You failed to upload ";
			}
		} else {
			return "You failed to upload ";
		}
	}
	
	@Override
	public Resource loadFile(String filename) {
		
		try {
           Path file = rootPath.resolve(filename);
           LOGGER.info("image in note service...."+file.toUri());
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
            	System.out.println("image...l,ml,");
                return resource;
            }else{
            	throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
        	throw new RuntimeException("FAIL!");
        }
	}
}
