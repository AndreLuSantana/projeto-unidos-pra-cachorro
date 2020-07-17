package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import model.services.LoadViewService;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuCadastrarAnimal;
	
	@FXML
	private MenuItem menuCadastrarAdotante;
	
	@FXML
	private MenuItem menuCadastrarUsuario;
	
	@FXML
	private MenuItem menuConsultarAnimal;
	
	@FXML
	private MenuItem menuConsultarAdotante;
	
	@FXML
	private MenuItem menuConsultarUsuario;
	
	@FXML
	public void onMenuItemCadastrarAnimalAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/CadastroAnimalView.fxml",x->{});
	}
		
	@FXML
	public void onMenuItemCadastrarUsuarioAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/CadastroUsuarioView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemCadastrarAdotanteAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/CadastroAdotanteView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemConsultarAnimalAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaAnimalView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemConsultarUsuarioAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaUsuarioView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemConsultarAdotanteAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaAdotanteView.fxml",x->{});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
}
