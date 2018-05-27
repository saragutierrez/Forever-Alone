package facades;

import java.io.IOException;
import java.sql.SQLException;

import com.mysql.cj.util.StringUtils;

import beans.Usuario;
import dao.UsuarioDAO;
import utils.SenhaUtil;

public class LoginFacade {

	public static Usuario verificaLogin(String email, String senha) {
		try {
			String psw = SenhaUtil.md5(senha);
			Usuario user = new UsuarioDAO().verificaLogin(email,psw);
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
	
}
