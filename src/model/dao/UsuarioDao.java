package model.dao;

import java.util.List;

import model.entities.Animal;
import model.entities.Usuario;

public interface UsuarioDao {

	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(int idUsuario);
	Boolean checkLogin(String emailUsuario, String senhaUsuario);
	Usuario findById(int idUsuario);
	List <Usuario> findByName(String nomeUsuario);
	List <Usuario> findAll();
	
}
