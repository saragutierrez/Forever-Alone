package facades;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.Encontro;
import dao.EncontroDAO;

public class EncontroFacade {
	
	public static List<Encontro> searchAll(){
		try {
			return new EncontroDAO().listarEncontros();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Listar encontros em que o cliente é o solicitado, ou seja, as solicitações de encontro RECEBIDOS PARA o cliente
	public static List<Encontro> searchAllFromClient(int idCliente){
		try {
			return new EncontroDAO().listarEncontrosRecebidos(idCliente);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Listar encontros em que o cliente solicitou a outros clientes, ou seja, as solicitações de encontro ENVIADOS pelo cliente
	public static List<Encontro> searchAllForClient(int idCliente){
		try {
			return new EncontroDAO().listarEncontrosEnviados(idCliente);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Encontro search(int idEncontro) {
		try {
			return new EncontroDAO().buscarEncontro(idEncontro);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void meet(Encontro encontro) {
		try {
			new EncontroDAO().solicitarEncontro(encontro);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/** Recebe vetor de int com id_solicitante (cliente) e id_solicitado **/
	public static void confirm(int[] dadosEncontro){
		try {
			new EncontroDAO().aceitarEncontro(dadosEncontro);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/** Recebe vetor de int com id_solicitante (cliente) e id_solicitado **/
	public static void recuse(int[] dadosEncontro){
		try {
			new EncontroDAO().recusarEncontro(dadosEncontro);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
