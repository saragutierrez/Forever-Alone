package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.EStatusConvidadoFesta;
import beans.Festa;
import utils.DataUtil;

public class FestaDAO {

	private final String SELECT_ALL = "SELECT * FROM tb_festa";
	private final String SELECT_ALL_FROM_CLIENT = "SELECT f.id_festa,f.nome_festa, f.data_festa, c.status_convidado "
			+ "FROM tb_festa f, tb_convidado c WHERE f.id_festa = c.id_festa AND c.id_convidado = ?;";
	private final String SELECT_ONE = "SELECT * FROM tb_festa WHERE id_festa = ?;";
	private final String INSERT = "INSERT INTO tb_festa(nome_festa, tema_festa, local_festa, data_festa) values (?,?,?,?);";
    private final String CONFIRM_PARTY = "INSERT INTO tb_convidado VALUES (?, ?, ?, 1);";
    private final String RECUSE_PARTY = "INSERT INTO tb_convidado VALUES (?, ?, ?, 2);";
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
        
    public FestaDAO() {
    }
    
	public List<Festa> listarFestas() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
	        
	        List<Festa> lista = new ArrayList<Festa>();
	
	        try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(SELECT_ALL);
	            rs = stmt.executeQuery();
	            while(rs.next()){
	                Festa aux =  new Festa();
                    aux.setIdFesta(rs.getInt(1));
                    aux.setNomeFesta(rs.getString(2));
                    aux.setTemaFesta(rs.getString(3));
                    aux.setLocalFesta(rs.getString(4));
                    aux.setDataFesta(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(5)));
	                lista.add(aux);
	            }
	            rs.close();
	            return lista;
	        }catch (SQLException e) {
	            throw new RuntimeException(e);
	        }finally {
	            con.close();
	        }                 
	    }

    
    //Listar festas em que o cliente foi convidado
    public List<Festa> listarFestasCliente(int idCliente) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
        
        List<Festa> lista = new ArrayList<Festa>();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_ALL_FROM_CLIENT);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            while(rs.next()){
                Festa aux =  new Festa();
                aux.setIdFesta(rs.getInt(1));
                aux.setNomeFesta(rs.getString(2));
                aux.setDataFesta(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(3)));
                switch (rs.getInt(4)) {
                case 0:
                	aux.setStatusConvidado(EStatusConvidadoFesta.ENVIADO);               
            		break;
                case 1:
                	aux.setStatusConvidado(EStatusConvidadoFesta.ACEITO);               
            		break;
                case 2:
                	aux.setStatusConvidado(EStatusConvidadoFesta.RECUSADO);               
            		break;
				default:
					break;
				}
                lista.add(aux);
            }
            rs.close();
            return lista;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }                 
    }
    
    public Festa buscarFesta(int idFesta) {
    	Festa aux = new Festa();
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(SELECT_ONE);
                stmt.setInt(1, idFesta);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    aux.setIdFesta(rs.getInt(1));
                    aux.setNomeFesta(rs.getString(2));
                    aux.setTemaFesta(rs.getString(3));
                    aux.setLocalFesta(rs.getString(4));
                    aux.setDataFesta(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(5)));
                    }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {con.close();} catch (SQLException e) {}
            }
            return aux;
    }

    public void adicionarFesta(Festa festa) throws InstantiationException, IllegalAccessException, IOException {
            try {
                    con = new ConnectionFactory().getConnection();
                    stmt = con.prepareStatement(INSERT);
                    stmt.setString(1, festa.getNomeFesta());
                    stmt.setString(2, festa.getTemaFesta());
                    stmt.setString(3, festa.getLocalFesta());
                    stmt.setTimestamp(4, DataUtil.formataDataHoraBeanParaSql(festa.getDataFesta()));
                    stmt.execute();
                    stmt.close();
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            } finally {
                    try{con.close();}catch(Exception e){}
            }
    }

    public void aceitarConviteFesta(int[] dadosConvite) throws InstantiationException, IllegalAccessException, IOException {
        try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(CONFIRM_PARTY);
                stmt.setInt(1, dadosConvite[0]);
                stmt.setInt(2, dadosConvite[1]);
                stmt.setInt(3, dadosConvite[2]);
                stmt.execute();
                stmt.close();
        } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
                try{con.close();}catch(Exception e){}
        }
}

    public void recusarConviteFesta(int[] dadosConvite) throws InstantiationException, IllegalAccessException, IOException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(RECUSE_PARTY);
            stmt.setInt(1, dadosConvite[0]);
            stmt.setInt(2, dadosConvite[1]);
            stmt.setInt(3, dadosConvite[2]);
            stmt.execute();
            stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
                try{con.close();}catch(Exception e){}
        }
}

}
