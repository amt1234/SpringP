package com.bridgeit.fundoonote.userservice.model;

public class EmailInfo {

	
	private String email;
	private long userId;
	
	private String token;
	
	public String getEmail() {
		return email;
	}
	public String setEmail(String email) {
		return this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public EmailInfo()
	{
		
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public EmailInfo(long userId, String token) {
		super();
		//this.email = email;
		this.userId = userId;
		this.token = token;
	}
	
}
