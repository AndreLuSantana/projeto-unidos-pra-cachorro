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
import model.entities.Adotante;

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
					"Update adotantes "
					+ "set nomeAdotante = ?, enderecoAdotante = ?, telefoneAdotante = ?, emailAdotante = ? "
					+ "WHERE idAdotante = ?");
			
			//st.setInt(2, new Animal().getIdAnimal());
			st.setString(1, obj.getNomeAdotante());
			st.setString(2, obj.getEnderecoAdotante());
			st.setString(3, obj.getTelefoneAdotante());
			st.setString(4, obj.getEmailAdotante());
			st.setInt(5, obj.getIdAdotante());
			
			st.executeUpdate();
			
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
			st = conn.prepareStatement("Delete from adotantes where idAdotante = ?");
			
			st.setInt(1, idAdotante);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<Adotante> findByName(String nomeAdotante) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM adotantes WHERE nomeAdotante like ? ");
			st.setString(1, "%" + nomeAdotante + "%");
			rs = st.executeQuery();
			
			List <Adotante> list = new ArrayList<>();
			
			while(rs.next()) {
				Adotante obj = new Adotante();
				obj.setIdAdotante(rs.getInt("idAdotante"));
				obj.setNomeAdotante(rs.getString("nomeAdotante"));
				obj.setEnderecoAdotante(rs.getString("enderecoAdotante"));
				obj.setTelefoneAdotante(rs.getString("telefoneAdotante"));
				obj.setEmailAdotante(rs.getString("emailAdotante"));
				
				list.add(obj);
			}
			return list;
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
					+ "order by idAdotante "
					);
			rs = st.executeQuery();
			
			List <Adotante> list = new ArrayList<>();
			
			while(rs.next()) {
				Adotante obj = new Adotante();
				obj.setIdAdotante(rs.getInt("idAdotante"));
				obj.setNomeAdotante(rs.getString("nomeAdotante"));
				obj.setEnderecoAdotante(rs.getString("enderecoAdotante"));
				obj.setTelefoneAdotante(rs.getString("telefoneAdotante"));
				obj.setEmailAdotante(rs.getString("emailAdotante"));
				
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
	
	@Override
	public Adotante findById(int idAdotante) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
						"Select * from adotantes "
						+ "where idAdotante = ?"
					);
			
			st.setInt(1, idAdotante);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Adotante obj = new Adotante();
				obj.setIdAdotante(rs.getInt("idAdotante"));
				obj.setNomeAdotante(rs.getString("nomeAdotante"));
				obj.setEnderecoAdotante(rs.getString("enderecoAdotante"));
				obj.setTelefoneAdotante(rs.getString("telefoneAdotante"));
				obj.setEmailAdotante(rs.getString("emailAdotante"));
				
				return obj;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	@Override
	public Boolean checkLogin(String emailAdotante, String senhaAdotante) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
