package model.services;

import java.util.List;

import model.dao.AdotanteDao;
import model.dao.DaoFactory;
import model.entities.Adotante;

public class AdotanteService {

	private AdotanteDao dao = DaoFactory.createAdotanteDao();

	public void insertOrUpdate(Adotante obj) {

		if(obj.getIdAdotante() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}

	public void deleteById(int idAdotante) {
		dao.deleteById(idAdotante);
	}

	public List<Adotante> findAll(){
		return dao.findAll();
	}

	public List<Adotante> findByName(String nomeAdotante){
		return dao.findByName(nomeAdotante);
	}

	public Adotante findByID(int idAdotante){
		return dao.findById(idAdotante);
	}

}
