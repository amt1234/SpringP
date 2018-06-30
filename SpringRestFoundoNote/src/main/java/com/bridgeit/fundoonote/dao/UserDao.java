package com.bridgeit.fundoonote.dao;

import com.bridgeit.fundoonote.model.User;

public interface UserDao {

	public long save(User user);
	
	public boolean login(long userId);
	
	public User checkEmail(String userEmail);
	public String userEmailRadies(User user);

}
