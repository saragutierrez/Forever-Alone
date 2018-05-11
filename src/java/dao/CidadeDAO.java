package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;

public class CidadeDAO {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public List<Cidade> listarCidades(int idEstado)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {

		List<Cidade> cidades = new ArrayList<Cidade>();

		try {
			con = new ConnectionFactory().getConnection();
			stmt = con.prepareStatement("SELECT * FROM tb_cidade WHERE id_estado = ?;");
            stmt.setInt(1, idEstado);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cidade aux = new Cidade();
				cidades.add(aux);
			}
			rs.close();
			return cidades;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
	}
	
	public Cidade buscarCidade(int idCidade) throws InstantiationException, IllegalAccessException, SQLException, IOException {
		Cidade aux = new Cidade();

		try {
			con = new ConnectionFactory().getConnection();
			stmt = con.prepareStatement("SELECT * FROM tb_cidade WHERE id_cidade = ?;");
            stmt.setInt(1, idCidade);
			rs = stmt.executeQuery();
			while (rs.next()) {
				aux.setIdCidade(rs.getInt(1));
				aux.setNomeCidade(rs.getString(2));
			}
			rs.close();
			return aux;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}

	}


}
