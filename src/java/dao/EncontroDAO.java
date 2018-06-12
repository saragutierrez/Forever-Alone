package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.EStatusEncontro;
import beans.Encontro;
import utils.DataUtil;

public class EncontroDAO {
	
	private final String SELECT_ALL = "SELECT * FROM tb_encontro";
	private final String SELECT_ALL_FROM_CLIENT = "SELECT id_encontro, id_solicitante_encontro, id_solicitado_encontro,"
			+ "lugar_encontro, data_encontro, status_encontro FROM tb_encontro WHERE id_solicitado_encontro = ?;";
	private final String SELECT_ALL_FOR_CLIENT = "SELECT id_encontro, id_solicitante_encontro, id_solicitado_encontro,"
			+ "lugar_encontro, data_encontro, status_encontro FROM tb_encontro WHERE id_solicitado_encontro = ?;";
	private final String SELECT_ONE = "SELECT * FROM tb_encontro WHERE id_encontro = ?;";
	private final String MEET = "INSERT INTO tb_encontro(id_solicitante_encontro, id_solicitado_encontro, lugar_encontro, "
							+ "data_encontro, status_encontro) values (?,?,?,?,"+EStatusEncontro.SOLICITADO.getIdStatusEncontro()+");";
	
	private final String CONFIRM_MEET = "UPDATE tb_encontro SET status_encontro = "
							+ EStatusEncontro.ACEITO.getIdStatusEncontro() + " WHERE id_solicitante_encontro = ? AND id_solicitado_encontro = ?;";
    private final String RECUSE_MEET = "UPDATE tb_convidado SET status_convidado = "
    						+ EStatusEncontro.RECUSADO.getIdStatusEncontro() + " WHERE id_solicitante_encontro = ? AND id_solicitado_encontro = ?;";
    
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
        
    public EncontroDAO() {
    }
    
	public List<Encontro> listarEncontros() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
	        
	        List<Encontro> lista = new ArrayList<Encontro>();
	
	        try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(SELECT_ALL);
	            rs = stmt.executeQuery();
	            while(rs.next()){
	                Encontro aux =  new Encontro();
                    aux.setIdEncontro(rs.getInt(1));
                    aux.setIdSolicitante(rs.getInt(2));
                    aux.setIdSolicitado(rs.getInt(3));
                    aux.setLocalEncontro(rs.getString(4));
                    aux.setDataEncontro(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(5)));
	                switch (rs.getInt(6)) {
	                case 0:
	                	aux.setStatus(EStatusEncontro.SOLICITADO);               
	            		break;
	                case 1:
	                	aux.setStatus(EStatusEncontro.ACEITO);               
	            		break;
	                case 2:
	                	aux.setStatus(EStatusEncontro.RECUSADO);               
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

    
    //Listar encontros em que o cliente é o solicitado, ou seja, as solicitações de encontro RECEBIDAS PARA o cliente
    public List<Encontro> listarEncontrosRecebidos(int idSolicitado) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
        
        List<Encontro> lista = new ArrayList<Encontro>();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_ALL_FROM_CLIENT);
            stmt.setInt(1, idSolicitado);
            rs = stmt.executeQuery();
            while(rs.next()){
            	Encontro aux =  new Encontro();
                aux.setIdEncontro(rs.getInt(1));
                aux.setIdSolicitante(rs.getInt(2));
                aux.setIdSolicitado(rs.getInt(3));
                aux.setLocalEncontro(rs.getString(4));
                aux.setDataEncontro(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(5)));
                switch (rs.getInt(6)) {
                case 0:
                	aux.setStatus(EStatusEncontro.SOLICITADO);               
            		break;
                case 1:
                	aux.setStatus(EStatusEncontro.ACEITO);               
            		break;
                case 2:
                	aux.setStatus(EStatusEncontro.RECUSADO);               
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
    
    //Listar encontros em que o cliente solicitou a outros clientes, ou seja, as solicitações de encontro ENVIADAS pelo cliente
    public List<Encontro> listarEncontrosEnviados(int idSolicitante) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
        
        List<Encontro> lista = new ArrayList<Encontro>();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_ALL_FOR_CLIENT);
            stmt.setInt(1, idSolicitante);
            rs = stmt.executeQuery();
            while(rs.next()){
            	Encontro aux =  new Encontro();
                aux.setIdEncontro(rs.getInt(1));
                aux.setIdSolicitante(rs.getInt(2));
                aux.setIdSolicitado(rs.getInt(3));
                aux.setLocalEncontro(rs.getString(4));
                aux.setDataEncontro(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(5)));
                switch (rs.getInt(6)) {
                case 0:
                	aux.setStatus(EStatusEncontro.SOLICITADO);               
            		break;
                case 1:
                	aux.setStatus(EStatusEncontro.ACEITO);               
            		break;
                case 2:
                	aux.setStatus(EStatusEncontro.RECUSADO);               
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
    
    public Encontro buscarEncontro(int idEncontro) {
    	Encontro aux = new Encontro();
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(SELECT_ONE);
                stmt.setInt(1, idEncontro);
                rs = stmt.executeQuery();
                if (rs.next()) {
                	aux.setIdEncontro(rs.getInt(1));
                    aux.setIdSolicitante(rs.getInt(2));
                    aux.setIdSolicitado(rs.getInt(3));
                    aux.setLocalEncontro(rs.getString(4));
                    aux.setDataEncontro(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(5)));
	                switch (rs.getInt(6)) {
	                case 0:
	                	aux.setStatus(EStatusEncontro.SOLICITADO);               
	            		break;
	                case 1:
	                	aux.setStatus(EStatusEncontro.ACEITO);               
	            		break;
	                case 2:
	                	aux.setStatus(EStatusEncontro.RECUSADO);               
	            		break;
					default:
						break;
					}
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {con.close();} catch (SQLException e) {}
            }
            return aux;
    }

    public void solicitarEncontro(Encontro encontro) throws InstantiationException, IllegalAccessException, IOException {
            try {
                    con = new ConnectionFactory().getConnection();
                    stmt = con.prepareStatement(MEET);
                    stmt.setInt(1, encontro.getIdSolicitante());
                    stmt.setInt(2, encontro.getIdSolicitado());
                    stmt.setString(3, encontro.getLocalEncontro());
                    stmt.setTimestamp(4, DataUtil.formataDataHoraBeanParaSql(encontro.getDataEncontro()));
                    stmt.execute();
                    stmt.close();
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            } finally {
                    try{con.close();}catch(Exception e){}
            }
    }

    public void aceitarEncontro(int[] dadosEncontro) throws InstantiationException, IllegalAccessException, IOException {
        try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(CONFIRM_MEET);
                stmt.setInt(1, dadosEncontro[0]);
                stmt.setInt(2, dadosEncontro[1]);
                stmt.executeUpdate();
                stmt.close();
        } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
                try{con.close();}catch(Exception e){}
        }
    }

    public void recusarEncontro(int[] dadosEncontro) throws InstantiationException, IllegalAccessException, IOException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(RECUSE_MEET);
            stmt.setInt(1, dadosEncontro[0]);
            stmt.setInt(2, dadosEncontro[1]);
            stmt.executeUpdate();
            stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
                try{con.close();}catch(Exception e){}
        }
    }
}
