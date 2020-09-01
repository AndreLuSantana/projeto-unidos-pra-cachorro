package model.services;

import java.util.List;

import model.dao.AnimalDao;
import model.dao.DaoFactory;
import model.entities.Animal;

public class AnimalService {
	
	
	private AnimalDao dao = DaoFactory.createAnimalDao();
	
	
	public void insertOrUpdate(Animal obj) {
		
		if(obj.getIdAnimal() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
	}
	
	public void deleteById(int idAnimal) {
		dao.deleteById(idAnimal);
	}
	
	public List<Animal> findAll(){
		return dao.findAll();
	}
	
	public Animal findByID(int idAnimal){
		return dao.findById(idAnimal);
	}
	
}
