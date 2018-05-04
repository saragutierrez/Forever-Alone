/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author luck
 * Classe que realiza a conexão com o banco de dados.
 * COnfigurar banco de dados no arquivo db.properties
 */
public class ConnectionFactory {

    public Connection getConnection() throws IOException {
        try {
            Properties prop = new Properties ();
            prop.load ((getClass().getResourceAsStream("/Forever-Alone/db.properties")));
            String dbDriver = prop.getProperty("db.driver");
            String dbUrl = prop.getProperty("db.url");
            String dbUser = prop.getProperty("db.user");
            String dbPwd = prop.getProperty("db.pwd");
            Class.forName(dbDriver);
            return DriverManager.getConnection(dbUrl, dbUser,
                    dbPwd);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
