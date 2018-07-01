package com.bridgeit.fundoonote.model;

public class EmailInfo {

	
	private String email;
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
	public EmailInfo(String email, String token) {
		super();
		this.email = email;
		this.token = token;
		
	}
}
