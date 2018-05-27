package utils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaUtil implements Serializable{

	public static String md5(String senha){
		String psw = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		psw = hash.toString(16);			
		return psw;
	}
	
	public static boolean compararSenha(String senha1, String senha2) {
		if(senha1.equals(senha2))
			return true;
		else
			return false;
	}

}
