package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.AnimalDao;
import model.entities.Animal;

public class AnimalDaoJDBC implements AnimalDao{
	
	private java.sql.Connection conn;
	
	public AnimalDaoJDBC(java.sql.Connection conn) {
		
		this.conn = conn;
	}

	@Override
	public void insert(Animal obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"Insert into animal "
					+ "(tamanho, peso, cor, dataResgate, vacinas, sexo, prenha, "
					+ "devolvidoParaRua, levadoCanil, castrado, disponivelAdocao, tratamentoRealizado) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
					st.setDouble(1, obj.getTamanhoAnimal());
					st.setDouble(2, obj.getPesoAnimal());
					st.setString(3, obj.getCorAnimal());
					st.setString(4, obj.getDataResgateAnimal());
					st.setString(5, obj.getVacinasAnimal());
					st.setString(6, obj.getSexoAnimal());
					st.setString(7, obj.getPrenhaAnimal());
					st.setString(8, obj.getDevolvidoParaRuaAnimal());
					st.setString(9, obj.getLevadoCanilAnimal());
					st.setString(10, obj.getCastradoAnimal());
					st.setString(11, obj.getDispAdocaoAnimal());
					st.setString(12, obj.getTratamentosAnimal());
					
					int rownsAfectted = st.executeUpdate();
					
					if(rownsAfectted > 0) {
						ResultSet rs = st.getGeneratedKeys();
							if(rs.next()) {
								int id = rs.getInt(1);
								obj.setIdAnimal(id);
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
	public void update(Animal obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"Update animal "
					+ "set tamanho = ?, peso = ?, cor = ?, dataResgate = ?, vacinas = ?, "
					+ "sexo = ?, prenha = ?, devolvidoParaRua = ?, levadoCanil = ?, "
					+ "castrado = ?, disponivelAdocao = ?, tratamentoRealizado = ? "
					+ "WHERE idAnimal = ?");
			
			st.setDouble(1, obj.getTamanhoAnimal());
			st.setDouble(2, obj.getPesoAnimal());
			st.setString(3, obj.getCorAnimal());
			st.setString(4, obj.getDataResgateAnimal());
			st.setString(5, obj.getVacinasAnimal());
			st.setString(6, obj.getSexoAnimal());
			st.setString(7, obj.getPrenhaAnimal());
			st.setString(8, obj.getDevolvidoParaRuaAnimal());
			st.setString(9, obj.getLevadoCanilAnimal());
			st.setString(10, obj.getCastradoAnimal());
			st.setString(11, obj.getDispAdocaoAnimal());
			st.setString(12, obj.getTratamentosAnimal());
			st.setInt(13, obj.getIdAnimal());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(int idAnimal) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Delete from animal where idAnimal = ?");
			
			st.setInt(1, idAnimal);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Animal findById(int idAnimal) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT *, date_format(dataResgate, '%d/%m/%Y') as 'dataFormatada' FROM animal WHERE idAnimal = ?");
			st.setInt(1, idAnimal);
			rs = st.executeQuery();
			if (rs.next()) {
				Animal obj = new Animal();
				obj.setIdAnimal(rs.getInt("idAnimal"));
				obj.setCorAnimal(rs.getString("cor"));
				obj.setTamanhoAnimal(rs.getDouble("tamanho"));
				obj.setPesoAnimal(rs.getDouble("peso"));
				obj.setDataResgateAnimal(rs.getString("dataFormatada"));
				obj.setVacinasAnimal(rs.getString("vacinas"));
				obj.setSexoAnimal(rs.getString("sexo"));
				obj.setPrenhaAnimal(rs.getString("prenha"));
				obj.setDevolvidoParaRuaAnimal(rs.getString("devolvidoParaRua"));
				obj.setLevadoCanilAnimal(rs.getString("levadoCanil"));
				obj.setCastradoAnimal(rs.getString("castrado"));
				obj.setDispAdocaoAnimal(rs.getString("disponivelAdocao"));
				obj.setTratamentosAnimal(rs.getString("tratamentoRealizado"));
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
	public List<Animal> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"Select *, date_format(dataResgate, '%d/%m/%Y') as 'dataFormatada' from animal "
					+ "order by idAnimal "
					);
			rs = st.executeQuery();
			
			List <Animal> list = new ArrayList<>();
			
			while(rs.next()) {
				Animal obj = new Animal();
				obj.setIdAnimal(rs.getInt("idAnimal"));
				obj.setCorAnimal(rs.getString("cor"));
				obj.setTamanhoAnimal(rs.getDouble("tamanho"));
				obj.setPesoAnimal(rs.getDouble("peso"));
				obj.setDataResgateAnimal(rs.getString("dataFormatada"));
				obj.setVacinasAnimal(rs.getString("vacinas"));
				obj.setSexoAnimal(rs.getString("sexo"));
				obj.setPrenhaAnimal(rs.getString("prenha"));
				obj.setDevolvidoParaRuaAnimal(rs.getString("devolvidoParaRua"));
				obj.setLevadoCanilAnimal(rs.getString("levadoCanil"));
				obj.setCastradoAnimal(rs.getString("castrado"));
				obj.setDispAdocaoAnimal(rs.getString("disponivelAdocao"));
				obj.setTratamentosAnimal(rs.getString("tratamentoRealizado"));
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