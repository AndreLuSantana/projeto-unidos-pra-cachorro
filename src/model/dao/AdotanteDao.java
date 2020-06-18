package model.dao;

import java.util.List;

import model.entities.Adotante;

public interface AdotanteDao {

	void insert(Adotante obj);
	void update(Adotante obj);
	void deleteByName(String nomeAdotante);
	Adotante findByName(String nomeAdotante);
	List <Adotante> findAll();
	
}