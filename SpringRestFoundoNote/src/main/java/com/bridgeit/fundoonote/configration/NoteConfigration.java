package com.bridgeit.fundoonote.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bridgeit.fundoonote.noteservice.dao.INoteDao;
import com.bridgeit.fundoonote.noteservice.dao.NoteDao;
import com.bridgeit.fundoonote.noteservice.service.INoteService;
import com.bridgeit.fundoonote.noteservice.service.NoteService;

@Configuration
@ComponentScan(basePackages="com.bridgeit.fundoonote")
public class NoteConfigration {

//	@Bean
//	INoteService iNoteService() {
//		NoteService noteService=new NoteService();
//		return noteService;
//	}
//	
//	@Bean
//	INoteDao iNoteDao() {
//		NoteDao noteDao=new NoteDao();
//		return noteDao;
//	}
}
