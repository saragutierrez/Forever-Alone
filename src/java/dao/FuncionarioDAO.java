package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Funcionario;
import utils.DataUtil;

public class FuncionarioDAO {
	    
		private final String SELECT_ALL = "SELECT * FROM tb_funcionario;";
		private final String SELECT_ONE = "SELECT * FROM tb_funcionario WHERE id_funcionario = ?;";
		private final String INSERT = "INSERT INTO tb_funcionario(nome_funcionario, email_funcionario, "
	                    + "senha_funcionario, data_registro) values (?,?,?,?);";
	    private final String UPDATE = "UPDATE tb_funcionario SET nome_funcionario = ?, email_funcionario = ?, senha_funcionario = ? WHERE id_funcionario = ?;";
	    private final String DELETE = "UPDATE tb_funcionario SET data_exclusao = ? id_funcionario = ?;";
	    Connection con = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	        
	    public FuncionarioDAO() {
	    }
	    
	    public List<Funcionario> listarFuncionarios() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException{
	        
	        List<Funcionario> lista = new ArrayList<Funcionario>();

	        try {
	            con = new ConnectionFactory().getConnection();
	            stmt = con.prepareStatement(SELECT_ALL);
	            rs = stmt.executeQuery();
	            while(rs.next()){
	                Funcionario aux =  new Funcionario();
	                aux.setNome(rs.getString(3));
	                aux.setEmail(rs.getString(4));
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
	    
	    public void adicionarFuncionario(Funcionario funcionario) throws InstantiationException, IllegalAccessException, IOException {
	            try {
	                    con = new ConnectionFactory().getConnection();
	                    stmt = con.prepareStatement(INSERT);
	                    stmt.setString(1, funcionario.getNome());
	                    stmt.setString(2, funcionario.getEmail());
	                    stmt.setString(3, funcionario.getSenha());
	                    stmt.setTimestamp(4, DataUtil.formataDataHoraBeanParaSql(funcionario.getDataRegistro()));
	                    stmt.execute();
	                    stmt.close();
	            } catch (SQLException e) {
	                    throw new RuntimeException(e);
	            } finally {
	                    try{con.close();}catch(Exception e){}
	            }
	    }
	    
	    public Funcionario buscarFuncionario(int id) {
	    	Funcionario aux = new Funcionario();
	            try {
	                con = new ConnectionFactory().getConnection();
	                stmt = con.prepareStatement(SELECT_ONE);
	                stmt.setInt(1, id);
	                rs = stmt.executeQuery();
	                if (rs.next()) {
	                    aux.setIdUsuario(rs.getInt(2));
	                    aux.setNome(rs.getString(3));
	                    aux.setEmail(rs.getString(4));
	                    }
	            }catch (Exception e) {
	                throw new RuntimeException(e);
	            }finally {
	                try {con.close();} catch (SQLException e) {}
	            }
	            return aux;
	    }
	    
	    public void alterarFuncionario(Funcionario funcionario) {
	            try {
	                con = new ConnectionFactory().getConnection();
	                stmt = con.prepareStatement(UPDATE);
	                stmt.setString(1, funcionario.getNome());
                    stmt.setString(2, funcionario.getEmail());
                    stmt.setString(3, funcionario.getSenha());
                    stmt.setInt(4, funcionario.getIdUsuario());
                    stmt.executeUpdate();
                    stmt.close();
	            } catch (Exception e) {
	                    throw new RuntimeException(e);
	            }finally{
	                    try {con.close();} catch (SQLException e) {}
	            }
	    }

	    public void deletarFuncionario(int idFuncionario) throws SQLException, InstantiationException, IllegalAccessException, IOException {
	    	con = new ConnectionFactory().getConnection();
	    	stmt = con.prepareStatement(DELETE);
	        try {
	            stmt.setTimestamp(1, DataUtil.formataDataHoraBeanParaSql(new Date()));
	            stmt.setInt(2, idFuncionario);
	            stmt.executeUpdate();
	        } finally{
	            stmt.close();
	        }
	    }
	    
}
