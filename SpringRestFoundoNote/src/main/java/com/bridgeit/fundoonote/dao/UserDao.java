package com.bridgeit.fundoonote.dao;

import com.bridgeit.fundoonote.model.User;

public interface UserDao {

	public int save(User user);
	
	public boolean login(long userId);
	
	public User checkEmail(String userEmail);

}
