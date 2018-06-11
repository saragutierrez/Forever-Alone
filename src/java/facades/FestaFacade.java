package facades;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import beans.Festa;
import dao.FestaDAO;

public class FestaFacade implements Serializable{

	public static List<Festa> searchAll(){
		try {
			return new FestaDAO().listarFestas();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Festa> searchAll(int idCliente){
		try {
			return new FestaDAO().listarFestasCliente(idCliente);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Festa search(int idFesta) {
		try {
			return new FestaDAO().buscarFesta(idFesta);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void insert(Festa festa) {
		try {
			new FestaDAO().adicionarFesta(festa);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/** Recebe vetor de int com id_convidado (cliente) id_funcionario (promotor) e id_festa **/
	public static void invite(int[] convidadoFesta){
		try {
			new FestaDAO().convidarCliente(convidadoFesta);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/** Recebe vetor de int com id_convidado (cliente) e id_festa **/
	public static void confirm(int[] dadosConvite){
		try {
			new FestaDAO().aceitarConviteFesta(dadosConvite);			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/** Recebe vetor de int com id_convidado (cliente) e id_festa **/
	public static void recuse(int[] dadosConvite){
		try {
			new FestaDAO().recusarConviteFesta(dadosConvite);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}