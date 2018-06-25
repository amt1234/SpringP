package com.bridgeit.fundoonote.dao;

import com.bridgeit.fundoonote.model.User;

public interface UserDao {

	public String save(User user);
	
	public boolean login(long userId);
	
	public User checkEmail(String userEmail);
	
	public boolean checkPassword(String password);
	/*public User checkEmail2(String userEmail,String userPassword);*/

}
