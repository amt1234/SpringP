package com.bridgeit.fundoonote.services;

import com.bridgeit.fundoonote.model.User;

public interface UserService {
	public String save(User user);

	public boolean check(String userEmail,String password);
}
