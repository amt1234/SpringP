package com.bridgeit.fundoonote.userservice.dao;

import java.util.List;

import com.bridgeit.fundoonote.userservice.model.User;
import com.bridgeit.fundoonote.userservice.model.UserProfile;

public interface UserDao {

	public long save(User user);

	public boolean login(long userId);

	public User checkEmail(String userEmail);

	public boolean updateUser(User user);

	public User checkId(long userId);
	public List<User> userList();
}
