package com.bridgeit.fundoonote.services;

import com.bridgeit.fundoonote.model.RegistrationDTO;
import com.bridgeit.fundoonote.model.User;

public interface UserService {
	public boolean registationSave(RegistrationDTO registrationDTO);
	//public boolean getUserTokenVerify(String token);
	public boolean check(String userEmail,String password);
	public boolean getUserTokenVerify(String token);
	//public void forgotUserPassword(User user);
	
	public boolean forgotUserPassword(RegistrationDTO registrationDTO, String emailId,String token);

}
