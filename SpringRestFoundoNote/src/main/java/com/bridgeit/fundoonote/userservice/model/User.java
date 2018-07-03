package com.bridgeit.fundoonote.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.bridgeit.fundoonote.userservice.validation.Phone;


@Entity
@Table(name = "FoundooTable")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	
	@NotEmpty
	@Size(min=2, message="Name should have atleast 2 characters")
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
	
	public User() {}
	
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
