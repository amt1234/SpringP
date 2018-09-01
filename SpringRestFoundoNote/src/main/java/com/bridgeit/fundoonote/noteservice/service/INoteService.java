package com.bridgeit.fundoonote.noteservice.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.model.WebScrap;

public interface INoteService {

	public Note createUserNote(Note note,String token);
	public boolean updateUserNote(Note note,String token);
	public List<Note> listOfNote(String token);
	public boolean deleteUserNote(String token, long noteid);
	public String uploadFile(MultipartFile file);
	public Resource loadFile(String filename);
	public List<Note> getCollaboratedNotes(String token);
	public boolean removeWebScrap(long id, WebScrap webScrap);
}
