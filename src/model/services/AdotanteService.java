package model.services;

import java.util.List;

import model.dao.AdotanteDao;
import model.dao.DaoFactory;
import model.entities.Adotante;

public class AdotanteService {

private AdotanteDao dao = DaoFactory.createAdotanteDao();
	

	public List<Adotante> findAll(){
		
		return dao.findAll();
	}
	
}
