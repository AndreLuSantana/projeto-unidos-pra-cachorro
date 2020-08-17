package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {

private UsuarioDao dao = DaoFactory.createUsuarioDao();


public void insertOrUpdate(Usuario obj) {
	
	if(obj == null) {
		dao.insert(obj);
	}else {
		dao.update(obj);
	}
}

public void deleteById(int idUsuario) {
	dao.deleteById(idUsuario);
}

public List<Usuario> findAll(){
	return dao.findAll();
}

public List<Usuario> findByName(String nomeUsuario){
	return dao.findByName(nomeUsuario);
}
	
}
