package facades;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Cidade;
import beans.Estado;
import dao.CidadeDAO;
import dao.EstadoDAO;

public class EnderecoFacade {
	
    public static List<Estado> listarEstados() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
        // Vai no BD buscar todos os estados, em uma lista
        EstadoDAO dao = new EstadoDAO();
        List<Estado> estados = dao.listarEstados();
        return estados;
    }

    public static List<Cidade> listarCidades(int idEstado) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
    	// Vai no BD buscar todas as cidades deste estado, em uma lista
    	CidadeDAO dao = new CidadeDAO();
		List<Cidade> cidades = dao.listarCidades(idEstado);
		return cidades;
    }
    
    public static Estado buscarEstado(int idEstado) throws InstantiationException, IllegalAccessException, SQLException, IOException {
    	//Vai no BD buscar estado
    	EstadoDAO dao = new EstadoDAO();
    	Estado estado = dao.buscarEstado(idEstado);
    	return estado;
    }
    
    public static Cidade buscarCidade(int idCidade) throws InstantiationException, IllegalAccessException, SQLException, IOException {
    	//Vai no BD buscar cidade
    	CidadeDAO dao = new CidadeDAO();
    	Cidade cidade = dao.buscarCidade(idCidade);
    	return cidade;
    }
}
