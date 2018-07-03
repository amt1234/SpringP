package com.bridgeit.fundoonote.userservice.utility;

public interface IJwtProgram {

	public String createJWT(long id, String issuer, String subject, long ttlMillis);
	public long parseJWT(String jwt);
}
