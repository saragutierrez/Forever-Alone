/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.EHomeUsuario;
import beans.Usuario;


/**
 *
 * @author luck
 */
public class UsuarioDAO {

	private static final String[] TIPO_USUARIO = {"Admin","Funcionario","Cliente"};
	public static final String VERIFY_FUNCIONARIO = "SELECT * FROM tb_funcionario WHERE email_funcionario = ? AND senha_funcionario = ? AND data_exclusao is null;";
	public static final String VERIFY_CLIENTE = "SELECT * FROM tb_cliente WHERE email_cliente = ? AND senha_cliente = ? AND data_exclusao is null;";
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public static String getTipoUsuario(int idTipo) {
		return TIPO_USUARIO[idTipo-1];
	}
    
    // Verifica o login no sistema
    // 1 - Usuario ativo
    // 2 - email e senha validos
    // Retorna o usuario
    public Usuario verificaLogin(String email, String senha) throws IOException, SQLException, InstantiationException, IllegalAccessException{
    	boolean logou = false;
	    Usuario user = new Usuario();
	        
	        try {
	            con = new ConnectionFactory().getConnection();
	            //Verificando funcionario
	            stmt = con.prepareStatement(VERIFY_FUNCIONARIO);
	            stmt.setString(1, email);
	            stmt.setString(2, senha);
	            
	            rs = stmt.executeQuery();
	            while(rs.next()){
	                //Verificando se o funcionario e Admin
	                if(rs.getBoolean(1)) {
	                	user.setTipoUsuario(getTipoUsuario(1));
	                	user.setHome(EHomeUsuario.ADMIN);	                	
	                }
	                else {
	                	user.setTipoUsuario(getTipoUsuario(2));
	                	user.setHome(EHomeUsuario.FUNCIONARIO);
	                }
	                user.setIdUsuario(rs.getInt(2));
	                user.setNome(rs.getString(3));
	                logou = true;
	            }
	            if(!logou) {
	            	//Verificando cliente
		            stmt = con.prepareStatement(VERIFY_CLIENTE);
		            stmt.setString(1, email);
		            stmt.setString(2, senha);
		            rs = stmt.executeQuery();
		            while(rs.next()){
		                user.setTipoUsuario(getTipoUsuario(3));
	                	user.setHome(EHomeUsuario.CLIENTE);
		                user.setIdUsuario(rs.getInt(1));
		                user.setNome(rs.getString(2));
		                logou = true;
		            }
	            }
	            rs.close();
	            return user;
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }finally {
	            con.close();
	        }
    }

}
