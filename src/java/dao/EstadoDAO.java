/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.java.beans.Estado;

/**
 *
 * @author luck
 */
public class EstadoDAO {

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public List<Estado> listarEstados()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {

		List<Estado> estados = new ArrayList<Estado>();

		try {
			con = new ConnectionFactory().getConnection();
			stmt = con.prepareStatement("SELECT * FROM tb_estado;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estado aux = new Estado();
				estados.add(aux);
			}
			rs.close();
			return estados;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
	}

	public Estado buscarEstado(int idEstado) throws InstantiationException, IllegalAccessException, SQLException, IOException {

		try {
			Estado aux = null;
			con = new ConnectionFactory().getConnection();
			stmt = con.prepareStatement("SELECT * FROM tb_estado WHERE id_estado = ?;");
            stmt.setInt(1, idEstado);
			rs = stmt.executeQuery();
			while (rs.next()) {
				aux = new Estado();
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
