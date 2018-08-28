package com.bridgeit.fundoonote.userservice.model;

public class UserProfile {

	private String userProfileImage;
	

	private String userEmail;
	private String userName;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public UserProfile() {
		
	}

	public UserProfile( String userEmail, String userName,String userProfileImage) {
		super();
		
		this.userEmail = userEmail;
		this.userName = userName;
		this.userProfileImage=userProfileImage;
	}
	
}
