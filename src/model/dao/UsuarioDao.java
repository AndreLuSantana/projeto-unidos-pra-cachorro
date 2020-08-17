package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDao {

	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(int idUsuario);
	Boolean checkLogin(String emailUsuario, String senhaUsuario);
	List <Usuario> findByName(String nomeUsuario);
	List <Usuario> findAll();
	
}
