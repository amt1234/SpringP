package jwt;

public class JwtMain {

	public static void main(String[] args) {
	JwtProgram jwtProgram=new JwtProgram();
	System.out.println("JwtToken");
	String token=jwtProgram.createJWT("101", "madhuri", "jwtToken", 5050550);
	System.out.println("Token : "+token);
	
	String tokenToKey=jwtProgram.parseJWT(token);
	//System.out.println("Key : "+tokenToKey);
	
	}

}
