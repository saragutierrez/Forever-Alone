/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author luck
 */
public class UsuarioDAO {

    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    //método não está pronto
    public void verificaLogin(String login, String senha, String tipoUsuario){
    }

}
