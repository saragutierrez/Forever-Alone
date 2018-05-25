package facades;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.mysql.cj.util.StringUtils;

import beans.Usuario;
import dao.UsuarioDAO;

public class LoginFacade {

	public static Usuario verificaLogin(String login, String senha) {
		try {
			String psw = md5(senha);
			Usuario user = new UsuarioDAO().verificaLogin(login,psw);
			if(!StringUtils.isNullOrEmpty(user.getTipoUsuario())){
				return user;
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    private static String md5(String senha){
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

}
