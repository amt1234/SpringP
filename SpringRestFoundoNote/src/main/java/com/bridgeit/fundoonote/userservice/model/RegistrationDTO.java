package com.bridgeit.fundoonote.userservice.model;

import javax.persistence.Transient;

public class RegistrationDTO {

	private String userName;
	private String userEmail;
	private String password;
	@Transient
	private String confirmPassword;
	private String phoneNumber;
	private String dateOfBirth;
	private String UserProfileImage;

	public String getUserProfileImage() {
		return UserProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		UserProfileImage = userProfileImage;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
}
