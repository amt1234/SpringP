package com.bridgeit.Dao;

import java.util.List;
import com.bridgeit.model.User;

public interface UserDao {

	public List<User> listAllUser(User user);
	public boolean findUserByEmail(User user);
	public boolean findUser(User user);
	public int addUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	
}
