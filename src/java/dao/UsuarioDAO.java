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

import beans.Usuario;


/**
 *
 * @author luck
 */
public class UsuarioDAO {

	private static final String[] TIPO_USUARIO = {"Admin","Funcionario","Cliente"};
	public static final String VERIFY_FUNCIONARIO = "SELECT * FROM tb_funcionario where login = ? and senha = ? and status = true;";
	public static final String VERIFY_CLIENTE = "SELECT * FROM tb_cliente where login = ? and senha = ? and status = true;";
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public static String getTipoUsuario(int idTipo) {
		return TIPO_USUARIO[idTipo];
	}
    
    // Verifica o login no sistema
    // 1 - Usuario ativo
    // 2 - login e senha validos
    // Retorna o usuario
    public Usuario verificaLogin(String login, String senha) throws IOException, SQLException, InstantiationException, IllegalAccessException{
    	boolean logou = false;
	    Usuario user = new Usuario();
	        
	        try {
	            con = new ConnectionFactory().getConnection();
	            stmt.setString(1, login);
	            stmt.setString(2, senha);
	            
	            //Verificando funcionario
	            stmt = con.prepareStatement(VERIFY_FUNCIONARIO);
	            rs = stmt.executeQuery();
	            while(rs.next()){
	                //Verificando se o funcionario � Admin
	                if(rs.getBoolean(1))
	                	user.setTipoUsuario(getTipoUsuario(1));
	                else
	                	user.setTipoUsuario(getTipoUsuario(2));
	                user.setIdUsuario(rs.getInt(2));
	                user.setNome(rs.getString(3));
	                logou = true;
	            }
                    rs.close();
	            if(!logou) {
	            	//Verificando cliente
		            stmt = con.prepareStatement(VERIFY_CLIENTE);
		            rs = stmt.executeQuery();
		            while(rs.next()){
		                user.setTipoUsuario(getTipoUsuario(3));
		                user.setIdUsuario(rs.getInt(2));
		                user.setNome(rs.getString(3));
		                logou = true;
		            }
                            rs.close();
	            }
	            return user;
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }finally {
	            con.close();
	        }

    }

}
