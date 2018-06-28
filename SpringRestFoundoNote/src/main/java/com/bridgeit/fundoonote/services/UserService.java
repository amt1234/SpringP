package com.bridgeit.fundoonote.services;

import com.bridgeit.fundoonote.model.RegistrationDTO;

public interface UserService {
	public boolean registationSave(RegistrationDTO registrationDTO);

	public boolean check(String userEmail,String password);

}
