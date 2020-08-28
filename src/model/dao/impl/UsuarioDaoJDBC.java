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
					+ "set nomeUsuario = ?, senhaUsuario = ?, emailUsuario = ? "
					+ "WHERE idUsuario = ?");
			
			st.setString(1, obj.getNomeUsuario());
			st.setString(2, obj.getSenhaUsuario());
			st.setString(3, obj.getEmailUsuario());
			st.setInt(4, obj.getIdUsuario());
			
			st.executeUpdate();
			
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
			st = conn.prepareStatement("Delete from usuarios where idUsuario = ?");
			
			st.setInt(1, idUsuario);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}
	
	@Override
	public Usuario findById(int idUsuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM usuarios WHERE idUsuario = ?");
			st.setInt(1, idUsuario);
			rs = st.executeQuery();
			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setIdUsuario(rs.getInt("idUsuario"));
				obj.setNomeUsuario(rs.getString("nomeUsuario"));
				obj.setEmailUsuario(rs.getString("emailUsuario"));
				obj.setSenhaUsuario(rs.getString("senhaUsuario"));
				
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
	public List<Usuario> findByName(String nomeUsuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM usuarios WHERE nomeUsuario like ? ");
			st.setString(1, "%" + nomeUsuario + "%");
			rs = st.executeQuery();
			
			List <Usuario> list = new ArrayList<>();
			
			while(rs.next()) {
				Usuario obj = new Usuario();
				obj.setIdUsuario(rs.getInt("idUsuario"));
				obj.setNomeUsuario(rs.getString("nomeUsuario"));
				obj.setEmailUsuario(rs.getString("emailUsuario"));
				obj.setSenhaUsuario(rs.getString("senhaUsuario"));

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
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"Select * from usuarios "
					+ "order by idUsuario "
					);
			rs = st.executeQuery();
			
			List <Usuario> list = new ArrayList<>();
			
			while(rs.next()) {
				Usuario obj = new Usuario();
				obj.setIdUsuario(rs.getInt("idUsuario"));
				obj.setNomeUsuario(rs.getString("nomeUsuario"));
				obj.setEmailUsuario(rs.getString("emailUsuario"));
				
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
