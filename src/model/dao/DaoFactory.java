package model.dao;

import db.DB;
import model.dao.impl.AdotanteDaoJDBC;
import model.dao.impl.AnimalDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

	public static AdotanteDao createAdotanteDao() {
		return new AdotanteDaoJDBC(DB.getConnection());
	}
	
	public static AnimalDao createAnimalDao() {
		return  new AnimalDaoJDBC(DB.getConnection());
	}
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
}
