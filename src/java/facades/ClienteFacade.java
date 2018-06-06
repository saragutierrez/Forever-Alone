/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import beans.Cliente;
import dao.ClienteDAO;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luck
 */
public class ClienteFacade implements Serializable{
        
    public static List<Cliente> searchAll() throws InstantiationException, IllegalAccessException, IOException{
        //Busca lista de clientes no banco de dados e retorna um List<Cliente>
    	List<Cliente> lista = new ArrayList<Cliente>();
        try {
            lista = new ClienteDAO().listarClientes();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static Cliente search(int id){
        //Busca id do cliente a ser visualizado no parametro da página
        return new ClienteDAO().buscarCliente(id);
    }
    
    public static void insert(Cliente c) throws InstantiationException, IllegalAccessException, IOException{
        new ClienteDAO().adicionarCliente(c);        
    }
    
    public static void update(Cliente c){
        try {
			new ClienteDAO().alterarCliente(c);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void delete(int id) throws InstantiationException, IllegalAccessException, IOException{
        try {
            //Busca cliente no banco de dados e deleta do banco de dados
            new ClienteDAO().deletarCliente(id);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
