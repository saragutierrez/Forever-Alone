/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luck
 */
public class ClienteDAO {
    
	//Compatibilizar Query's com o banco de dados
	private final String INSERT = "INSERT INTO tb_cliente(cpf_cliente, nome_cliente, email_cliente, "
                    + "data_cliente, rua_cliente, nr_cliente, cep_cliente, id_cidade) values (?,?,?,?,?,?,?,?);";
    private final String UPDATE = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, "
                    + "data_cliente = ?, rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, id_cidade = ? WHERE id_cliente = ?;";
    private final String DELETE = "DELETE FROM tb_cliente WHERE id_cliente=?;";
    private final String SELECT_ONE = "SELECT * FROM tb_cliente WHERE id_cliente = ?;";
    private final String SELECT_ALL = "SELECT * FROM tb_cliente;";
    
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
    
    public Cliente buscarCliente(int id) {
    	Cliente aux = new Cliente();
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(SELECT_ONE);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    aux.setIdUsuario(rs.getInt(1));
                    aux.setCpf(rs.getString(2));
                    aux.setNome(rs.getString(3));
                    aux.setEmail(rs.getString(4));
                    
                    //converter sql date para java date 
                    java.sql.Date sqlDate = rs.getDate(5);
                    java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                    aux.setDataRegistro(utilDate);                    
                    }
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
                    stmt = con.prepareStatement(INSERT);
                    stmt.setString(1, cliente.getCpf());
                    stmt.setString(2, cliente.getNome());
                    stmt.setString(3, cliente.getEmail());
                    
                    //converter java date para sql date
                    java.sql.Date sqlDate = new java.sql.Date(cliente.getPerfil().getDataNasc().getTime());
                    
                    stmt.setDate(4, sqlDate);
                    stmt.setString(5, cliente.getEndereco().getRua());
                    stmt.setInt(7, cliente.getEndereco().getCep());
                    stmt.setInt(8, cliente.getEndereco().getCidade().getIdCidade());
                    stmt.execute();
                    stmt.close();
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            } finally {
                    try{con.close();}catch(Exception e){}
            }
    }
    
    public void alterarCliente(Cliente cliente) {
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(UPDATE);
                stmt.setString(1, cliente.getCpf());
                stmt.setString(2, cliente.getNome());
                stmt.setString(3, cliente.getEmail());
                
                //converter java date para sql date
                java.sql.Date sqlDate = new java.sql.Date(cliente.getPerfil().getDataNasc().getTime());
                
                stmt.setDate(4, sqlDate);
                stmt.setString(5, cliente.getEndereco().getRua());
                stmt.setInt(7, cliente.getEndereco().getCep());
                stmt.setInt(8, cliente.getEndereco().getCidade().getIdCidade());
                stmt.execute();
                stmt.close();
            } catch (Exception e) {
                    throw new RuntimeException(e);
            }finally{
                    try {con.close();} catch (SQLException e) {}
            }
    }

    public void deletarCliente(int idCliente) throws SQLException, InstantiationException, IllegalAccessException, IOException {
    	con = new ConnectionFactory().getConnection();
    	stmt = con.prepareStatement(DELETE);
        try {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } finally{
            stmt.close();
        }
    }
    
}
