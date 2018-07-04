package com.bridgeit.fundoonote.userservice.services;

import com.bridgeit.fundoonote.userservice.model.EmailInfo;
import com.bridgeit.fundoonote.userservice.model.RegistrationDTO;

public interface UserService {
	public boolean registationSave(RegistrationDTO registrationDTO,String link);

	public String check(String userEmail, String password);

	public boolean getUserTokenVerify(String token);

	public boolean forgotUserPassword(String token, String password);

	public boolean resetUserPassword(EmailInfo emailInfo,String link);

}
