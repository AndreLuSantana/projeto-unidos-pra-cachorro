package model.dao;

import java.util.List;

import model.entities.Adotante;
import model.entities.Animal;

public interface AnimalDao {

	void insert(Animal obj);
	void update(Animal obj);
	void deleteById(int idAnimal);
	Animal findById(int idAnimal);
	List <Animal> findAll();
	
}
