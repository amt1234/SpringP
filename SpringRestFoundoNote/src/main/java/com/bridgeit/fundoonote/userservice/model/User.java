package com.bridgeit.fundoonote.userservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.userservice.validation.Phone;

@Entity
@Table(name = "FoundooTable")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@NotEmpty
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String userName;

	@NotEmpty
	@Email
	private String userEmail;

	@NotEmpty
	private String password;

	@Phone
	private String phoneNumber;

	@NotEmpty
	private String dateOfBirth;

	private boolean isActiveUser;
	
	private String userProfileImage;
	
	//for collaborator
	@ManyToMany(mappedBy="users")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<Note> notes=new ArrayList<Note>();

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	
	
	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public User() {
		
	}

	public User(RegistrationDTO registrationDto) {
		this.userName = registrationDto.getUserName();
		this.userEmail = registrationDto.getUserEmail();
		this.password = registrationDto.getPassword();
		this.phoneNumber = registrationDto.getPhoneNumber();
		this.dateOfBirth = registrationDto.getDateOfBirth();
	}

	public boolean isActiveUser() {
		return isActiveUser;
	}

	public void setActiveUser(boolean isActiveUser) {
		this.isActiveUser = isActiveUser;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
