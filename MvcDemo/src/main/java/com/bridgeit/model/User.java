package com.bridgeit.model;

public class User {

	
	private Integer Userid;
	private String username;
	private String email;
	private String password;
	private long mobileNo;
	private String dob;
	
	public Integer getUserid() {
		return Userid;
	}
	public void setUserid(Integer userid) {
		Userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "User [Userid=" + Userid + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", mobileNo=" + mobileNo + ", dob=" + dob + "]";
	}
}
