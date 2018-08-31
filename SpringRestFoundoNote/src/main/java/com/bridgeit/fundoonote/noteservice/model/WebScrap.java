package com.bridgeit.fundoonote.noteservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WebScrap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long linkId;
	private String linkTitle;
	private String linkHost;
	private String linkImage;
	private String fullLink;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "noteId")
	@JsonIgnore
	private Note webScrapNote;
	
	public Note getWebScrapNote() {
		return webScrapNote;
	}
	public void setWebScrapNote(Note webScrapNote) {
		this.webScrapNote = webScrapNote;
	}
	public long getLinkId() {
		return linkId;
	}
	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}
	public String getLinkTitle() {
		return linkTitle;
	}
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
	public String getLinkHost() {
		return linkHost;
	}
	public void setLinkHost(String linkHost) {
		this.linkHost = linkHost;
	}
	public String getLinkImage() {
		return linkImage;
	}
	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}
	public String getFullLink() {
		return fullLink;
	}
	public void setFullLink(String fullLink) {
		this.fullLink = fullLink;
	}
	
}
