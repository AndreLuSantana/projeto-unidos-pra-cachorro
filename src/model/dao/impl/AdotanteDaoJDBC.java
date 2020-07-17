package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.AdotanteDao;
import model.entities.Adotante;
import model.entities.Animal;
import model.entities.Usuario;

public class AdotanteDaoJDBC implements AdotanteDao{
	
	private java.sql.Connection conn;
	
	public AdotanteDaoJDBC(java.sql.Connection coon) {
	
		this.conn = coon;
	}

	@Override
	public void insert(Adotante obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"Insert into adotantes "
					+ "(idAdotante, IdAnimal, nomeAdotante, enderecoAdotante, telefoneAdotante, emailAdotante  "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ? )",
					Statement.RETURN_GENERATED_KEYS);
			
					st.setInt(1, obj.getIdAdotante());
					st.setInt(2, new Animal().getIdAnimal());
					st.setString(3, obj.getNomeAdotante());
					st.setString(4, obj.getEnderecoAdotante());
					st.setString(5, obj.getTelefoneAdotante());
					st.setString(6, obj.getEmailAdotante());
					
					
					int rownsAfectted = st.executeUpdate();
					
					if(rownsAfectted > 0) {
						ResultSet rs = st.getGeneratedKeys();
							if(rs.next()) {
								int id = rs.getInt(1);
								obj.setIdAdotante(id);
							}
						DB.closeResultSet(rs);
					}	
					else {
						throw new DbException("Erro inesperado, nenhuma linha foi afetada.");
					}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Adotante obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"Update usuarios "
					+ "set idAdotante = ?, idAnimal = ?, nomeAdotante = ?, endercoAdotante = ?, telefoneAdotante = ?, emailAdotante = ? "
					+ "WHERE id = ?");
			
			st.setInt(1, obj.getIdAdotante());
			st.setInt(2, new Animal().getIdAnimal());
			st.setString(3, obj.getNomeAdotante());
			st.setString(4, obj.getEnderecoAdotante());
			st.setString(4, obj.getTelefoneAdotante());
			st.setString(4, obj.getEmailAdotante());
			
			st.executeQuery();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(int idAdotante) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Delete from usuarios where idAdotante = ?");
			
			st.setInt(1, idAdotante);
			
			st.executeQuery();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Adotante findByName(String nomeAdotante) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department WHERE nomeAdotante = ?");
			st.setString(1, nomeAdotante);
			rs = st.executeQuery();
			if (rs.next()) {
				Adotante obj = new Adotante();
				obj.setNomeAdotante(rs.getString("nomeAdotante"));
				obj.setEmailAdotante(rs.getString("emailAdotante"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Adotante> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"Select * from adotantes "
					+ "order by nomeAdotante "
					);
			rs = st.executeQuery();
			
			List <Adotante> list = new ArrayList<>();
			
			while(rs.next()) {
				Adotante obj = new Adotante();
				st.setInt(1, obj.getIdAdotante());
				st.setInt(2, new Animal().getIdAnimal());
				st.setString(3, obj.getNomeAdotante());
				st.setString(4, obj.getEnderecoAdotante());
				st.setString(5, obj.getTelefoneAdotante());
				st.setString(6, obj.getEmailAdotante());
				
				list.add(obj);
			}
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
}
