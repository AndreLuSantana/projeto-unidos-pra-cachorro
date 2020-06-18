package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Adotante;
import model.entities.Animal;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{
	
	private java.sql.Connection conn;
	
		public UsuarioDaoJDBC(java.sql.Connection conn) {
		
		this.conn = conn;
	}

	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"Insert into usuarios "
					+ "(idUsuario, nomeUsuario, senhaUsuario, emailUsuario "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
					st.setInt(1, obj.getIdUsuario());
					st.setString(2, obj.getNomeUsuario());
					st.setString(3, obj.getSenhaUsuario());
					st.setString(4, obj.getEmailUsuario());
					
					
					int rownsAfectted = st.executeUpdate();
					
					if(rownsAfectted > 0) {
						ResultSet rs = st.getGeneratedKeys();
							if(rs.next()) {
								int id = rs.getInt(1);
								obj.setIdUsuario(id);
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
	public void update(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"Update usuarios "
					+ "set idUsuario = ?, nomeUsuario = ?, senhaUsuario = ?, emailUsuario = ? "
					+ "WHERE id = ?");
			
			st.setInt(1, obj.getIdUsuario());
			st.setString(2, obj.getNomeUsuario());
			st.setString(3, obj.getSenhaUsuario());
			st.setString(4, obj.getEmailUsuario());
			
			st.executeQuery();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(int idUsuario) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Delete from usuarios where id = ?");
			
			st.setInt(1, idUsuario);
			
			st.executeQuery();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Usuario findByName(String nomeUsuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department WHERE nomeUsuario = ?");
			st.setString(1, nomeUsuario);
			rs = st.executeQuery();
			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setNomeUsuario(rs.getString("nomeUsuario"));
				obj.setEmailUsuario(rs.getString("emailUsuario"));
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
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"Select * from usuarios "
					+ "order by nomeUsuario "
					);
			rs = st.executeQuery();
			
			List <Usuario> list = new ArrayList<>();
			
			while(rs.next()) {
				Usuario obj = new Usuario();
				st.setInt(1, obj.getIdUsuario());
				st.setString(2, obj.getNomeUsuario());
				st.setString(3, obj.getSenhaUsuario());
				st.setString(4, obj.getEmailUsuario());
				
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
	public Boolean checkLogin(String emailUsuario, String senhaUsuario) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean check = false;
		try {
			
				st = conn.prepareStatement(
					"Select * from usuarios where "
					+ "emailUsuario = ? and senhaUsuario = ?"
					);
				st.setString(1, emailUsuario);
				st.setString(2, senhaUsuario);
				
				rs = st.executeQuery();
				
				if (rs.next()) {
					
					check = true;
				}
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return check;
	}
	
	
	
}
