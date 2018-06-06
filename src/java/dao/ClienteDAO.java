/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Cliente;
import beans.Perfil;
import beans.Preferencias;
import utils.DataUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author luck
 */
public class ClienteDAO {
    
	private final String SELECT_CLIENTE = "SELECT * FROM tb_cliente WHERE id_cliente = ? AND data_exclusao is null;";
	private final String SELECT_PERFIL = "SELECT * FROM tb_perfil WHERE id_cliente = ?;";
	private final String SELECT_PREFERENCIAS = "SELECT * FROM tb_preferencias WHERE id_perfil = ?;";
	private final String SELECT_ALL = "SELECT * FROM tb_cliente WHERE data_exclusao is null;";
	
	private final String INSERT_CLIENTE = "INSERT INTO tb_cliente(nome_cliente, email_cliente, senha_cliente, cpf_cliente, "
			+ "registro_cliente, endereco_cliente, cep_cliente, id_cidade) values (?,?,?,?,?,?,?,?);";
	private final String INSERT_PERFIL = "INSERT INTO tb_perfil(genero_perfil, escolaridade_perfil, cor_cabelo_perfil, cor_pele_perfil, "
			+ "dataNasc_perfil, descricao_perfil, id_cliente) values (?,?,?,?,?,?,?);";
	private final String INSERT_PREFERENCIAS = "INSERT INTO tb_preferencias(genero_preferencias, escolaridade_preferencias, cor_cabelo_preferencias, cor_pele_preferencias, "
			+ "idadeMin_preferencias, idadeMax_preferencias, id_perfil) values (?,?,?,?,?,?,?);";
	
	private final String UPDATE_CLIENTE = "UPDATE tb_cliente SET nome_cliente = ?, email_cliente = ?, senha_cliente = ?, cpf_cliente = ?, "
			+ "endereco_cliente = ?, cep_cliente = ?, id_cidade = ? WHERE id_cliente = ?;";
	private final String UPDATE_PERFIL = "UPDATE tb_perfil SET genero_perfil = ?, escolaridade_perfil = ?, cor_cabelo_perfil = ?, cor_pele_perfil = ?, "
			+ "dataNasc_perfil = ?, descricao_perfil = ? WHERE id_cliente = ?;";
	private final String UPDATE_PREFERENCIAS = "UPDATE tb_preferencias SET genero_preferencias = ?, escolaridade_preferencias = ?, cor_cabelo_preferencias = ?, cor_pele_preferencias = ?, "
			+ "idadeMin_preferencias = ?, idadeMax_preferencias = ? WHERE id_perfil = ?;";
	
	private final String DELETE = "UPDATE tb_cliente SET data_exclusao = ? WHERE id_cliente = ?;";
	
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
        
    public ClienteDAO() {
    }
    
    public List<Cliente> listarClientes() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{        
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cliente aux =  new Cliente();
                aux.setIdUsuario(rs.getInt(2));
                aux.setNome(rs.getString(3));
                aux.setEmail(rs.getString(4));                    
                aux.setCpf(rs.getString(5));
                lista.add(aux);
            }
            rs.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }                 
        return lista;
    }
    
    public Cliente buscarCliente(int id) {
    	Cliente aux = new Cliente();
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_CLIENTE);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                aux.setIdUsuario(rs.getInt(1));
                aux.setNome(rs.getString(2));
                aux.setEmail(rs.getString(3));                    
                aux.setCpf(rs.getString(5));
                aux.getEndereco().setRua(rs.getString(7));
                aux.getEndereco().setCep(rs.getInt(8));
                aux.getEndereco().getCidade().setIdCidade(rs.getInt(9));
                }
            rs.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {con.close();} catch (SQLException e) {}
        }
        aux.setPerfil(buscarPerfil(aux.getIdUsuario()));
        aux.setPreferencias(buscarPreferencias(aux.getPerfil().getIdPerfil()));
        return aux;
    }
    
    public Perfil buscarPerfil(int id) {
    	Perfil aux = new Perfil();
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_PERFIL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                aux.setIdPerfil(rs.getInt(1));
                aux.setGenero(rs.getString(2));
                aux.setEscolaridade(rs.getString(3));                    
                aux.setCorCabelo(rs.getString(4));
                aux.setCorPele(rs.getString(5));
                aux.setDataNasc(rs.getTimestamp(6));
                aux.setDescricao(rs.getString(7));
                }
            rs.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {con.close();} catch (SQLException e) {}
        }
        return aux;
    }
    public Preferencias buscarPreferencias(int id) {
    	Preferencias aux = new Preferencias();
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_PREFERENCIAS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                }
            rs.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {con.close();} catch (SQLException e) {}
        }
        return aux;
    }
    public void adicionarCliente(Cliente cliente) throws InstantiationException, IllegalAccessException, IOException {
    		try {
                    con = new ConnectionFactory().getConnection();
                    stmt = con.prepareStatement(INSERT_CLIENTE, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, cliente.getNome());
                    stmt.setString(2, cliente.getEmail());
                    stmt.setString(3, cliente.getSenha());
                    stmt.setString(4, cliente.getCpf());
                    stmt.setTimestamp(5, DataUtil.formataDataHoraBeanParaSql(new Date()));
                    stmt.setString(6, cliente.getEndereco().getRua());
                    stmt.setInt(7, cliente.getEndereco().getCep());
                    stmt.setInt(8, cliente.getEndereco().getCidade().getIdCidade());
                    stmt.execute();
                    if(!cliente.getPerfil().equals(null)) {
                    	rs = stmt.getGeneratedKeys();
                    	int idCliente = 0;
                    	if(rs.next())
                    		idCliente = rs.getInt(1);
                    	stmt.close();
                    	adicionarPerfilCliente(idCliente, cliente);
                    }else {
                    	stmt.close();
                    }
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            } finally {
                    try{con.close();}catch(Exception e){}
            }
    }
    
    public void adicionarPerfilCliente(int idCliente, Cliente cliente) throws InstantiationException, IllegalAccessException, IOException {
	    	try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(INSERT_PERFIL, Statement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, cliente.getPerfil().getGenero());
	            stmt.setString(2, cliente.getPerfil().getEscolaridade());
	            stmt.setString(3, cliente.getPerfil().getCorCabelo());
	            stmt.setString(4, cliente.getPerfil().getCorPele());
	            stmt.setTimestamp(5, DataUtil.formataDataHoraBeanParaSql(cliente.getPerfil().getDataNasc()));
	            stmt.setString(6, cliente.getPerfil().getDescricao());
	            stmt.setInt(7, idCliente);
	            stmt.execute();
	            if(!cliente.getPreferencias().equals(null)) {
	            	rs = stmt.getGeneratedKeys();
	            	int idPerfil = 0;
	            	if(rs.next())
	            		idPerfil = rs.getInt(1);
	            	stmt.close();
	            	adicionarPreferenciasCliente(idPerfil,cliente);	            	
	            }else {
	            	stmt.close();
	            }
	    } catch (SQLException e) {
	            throw new RuntimeException(e);
	    } finally {
	            try{con.close();}catch(Exception e){}
	    }
	}

	public void adicionarPreferenciasCliente(int idPerfil, Cliente cliente) throws InstantiationException, IllegalAccessException, IOException {
	    	try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(INSERT_PREFERENCIAS);
	            stmt.setString(1, cliente.getPreferencias().getGenero());
	            stmt.setString(2, cliente.getPreferencias().getEscolaridade());
	            stmt.setString(3, cliente.getPreferencias().getCorCabelo());
	            stmt.setString(4, cliente.getPreferencias().getCorPele());
	            stmt.setInt(5, cliente.getPreferencias().getIdadeInicial());
	            stmt.setInt(6, cliente.getPreferencias().getIdadeFinal());
	            stmt.setInt(7, idPerfil);
	            stmt.execute();
	            stmt.close();
	    } catch (SQLException e) {
	            throw new RuntimeException(e);
	    } finally {
	            try{con.close();}catch(Exception e){}
	    }
	}

	public void alterarCliente(Cliente cliente) throws InstantiationException, IllegalAccessException, IOException {
			try {
	                con = new ConnectionFactory().getConnection();
	                stmt = con.prepareStatement(UPDATE_CLIENTE);
	                stmt.setString(1, cliente.getNome());
	                stmt.setString(2, cliente.getEmail());
	                stmt.setString(3, cliente.getSenha());
	                stmt.setString(4, cliente.getCpf());
	                stmt.setString(5, cliente.getEndereco().getRua());
	                stmt.setInt(6, cliente.getEndereco().getCep());
	                stmt.setInt(7, cliente.getEndereco().getCidade().getIdCidade());
	                stmt.setInt(8, cliente.getIdUsuario());
	                stmt.execute();
	            	stmt.close();
	            	alterarPerfilCliente(cliente);
	        } catch (SQLException e) {
	                throw new RuntimeException(e);
	        } finally {
	                try{con.close();}catch(Exception e){}
	        }
	}
	
	public void alterarPerfilCliente(Cliente cliente) throws InstantiationException, IllegalAccessException, IOException {
	    	try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(UPDATE_PERFIL);
	            stmt.setString(1, cliente.getPerfil().getGenero());
	            stmt.setString(2, cliente.getPerfil().getEscolaridade());
	            stmt.setString(3, cliente.getPerfil().getCorCabelo());
	            stmt.setString(4, cliente.getPerfil().getCorPele());
	            stmt.setTimestamp(5, DataUtil.formataDataHoraBeanParaSql(cliente.getPerfil().getDataNasc()));
	            stmt.setString(6, cliente.getPerfil().getDescricao());
	            stmt.setInt(7, cliente.getPerfil().getIdPerfil());
	            stmt.execute();
	            stmt.close();
	            alterarPreferenciasCliente(cliente);
	    } catch (SQLException e) {
	            throw new RuntimeException(e);
	    } finally {
	            try{con.close();}catch(Exception e){}
	    }
	}
	
	public void alterarPreferenciasCliente(Cliente cliente) throws InstantiationException, IllegalAccessException, IOException {
	    	try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(UPDATE_PREFERENCIAS);
	            stmt.setString(1, cliente.getPreferencias().getGenero());
	            stmt.setString(2, cliente.getPreferencias().getEscolaridade());
	            stmt.setString(3, cliente.getPreferencias().getCorCabelo());
	            stmt.setString(4, cliente.getPreferencias().getCorPele());
	            stmt.setInt(5, cliente.getPreferencias().getIdadeInicial());
	            stmt.setInt(6, cliente.getPreferencias().getIdadeFinal());
	            stmt.setInt(7, cliente.getPreferencias().getIdPreferencias());
	            stmt.execute();
	            stmt.close();
	    } catch (SQLException e) {
	            throw new RuntimeException(e);
	    } finally {
	            try{con.close();}catch(Exception e){}
	    }
	}

    public void deletarCliente(int idCliente) throws SQLException, InstantiationException, IllegalAccessException, IOException {
    	con = new ConnectionFactory().getConnection();
    	stmt = con.prepareStatement(DELETE);
        try {
        	stmt.setTimestamp(1, DataUtil.formataDataHoraBeanParaSql(new Date()));
            stmt.setInt(2, idCliente);
            stmt.executeUpdate();
        } finally{
            stmt.close();
        }
    }
    
}
