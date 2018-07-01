package com.bridgeit.fundoonote.dao;

import com.bridgeit.fundoonote.model.User;

public interface UserDao {

	public long save(User user);
	
	public boolean login(long userId);
	
	public User checkEmail(String userEmail);
	//public int updateUser(String isActiveUser);

	public boolean updateUser(boolean isActiveUser);

}
