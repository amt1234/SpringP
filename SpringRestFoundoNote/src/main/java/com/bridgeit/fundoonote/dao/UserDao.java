package com.bridgeit.fundoonote.dao;

import com.bridgeit.fundoonote.model.User;

public interface UserDao {

	public long save(User user);
	public boolean login(long userId);
	
	public boolean check(User user);
	boolean check(String userEmail);
}
