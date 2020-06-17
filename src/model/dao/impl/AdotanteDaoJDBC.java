package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import model.dao.AdotanteDao;
import model.entities.Adotante;

public class AdotanteDaoJDBC implements AdotanteDao{
	
	private java.sql.Connection coon;
	
	public AdotanteDaoJDBC(java.sql.Connection coon) {
	
		this.coon = coon;
	}

	@Override
	public void insert(Adotante obj) {

		PreparedStatement st = null;
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
		}
		
		
	}

	@Override
	public void update(Adotante obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByName(String nomeAdotante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Adotante findByName(String nomeAdotante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adotante> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
