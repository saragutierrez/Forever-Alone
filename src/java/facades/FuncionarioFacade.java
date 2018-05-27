package facades;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Funcionario;
import dao.FuncionarioDAO;

public class FuncionarioFacade implements Serializable{

    public static List<Funcionario> searchAll() throws InstantiationException, IllegalAccessException, IOException{
        //Busca lista de funcionarios no banco de dados e retorna um List<Funcionario>
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try {
            lista = new FuncionarioDAO().listarFuncionarios();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static Funcionario search(int id){
        return new FuncionarioDAO().buscarFuncionario(id);
    }
    
    public static void insert(Funcionario f) {
    	try {
			new FuncionarioDAO().adicionarFuncionario(f);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}        
    }
    
    public static void update(Funcionario f){
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.alterarFuncionario(f);
    }
    
    public static void delete(int id) throws InstantiationException, IllegalAccessException, IOException{
        try {
        	//deleta logicamente o funcionario do banco do dados setando a data de exclusao
			new FuncionarioDAO().deletarFuncionario(id);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
