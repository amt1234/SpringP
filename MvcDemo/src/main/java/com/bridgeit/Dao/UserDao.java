package com.bridgeit.Dao;

import java.util.List;
import com.bridgeit.model.User;

public interface UserDao {

	public List<User> listAllUser();
	public int findUser();
	public int addUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	
}
