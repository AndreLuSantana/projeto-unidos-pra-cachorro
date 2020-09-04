package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.services.LoadViewService;
import model.services.TelaPrincipal;

public class MainViewController implements Initializable {
	
	@FXML
	private Menu telaPrincipal;
	
	@FXML
	private MenuItem irTelaPrincipal;
	
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
	public void onMenuAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadViewMain("/gui/MainView.fxml");
	}
	
	@FXML
	public void onMenuItemCadastrarAnimalAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AnimalFormCadastroView.fxml",x->{});
	}
		
	@FXML
	public void onMenuItemCadastrarUsuarioAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/UsuarioFormCadastroView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemCadastrarAdotanteAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AdotanteFormCadastroView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemConsultarAnimalAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AnimalConsultaView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemConsultarUsuarioAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/UsuarioConsultaView.fxml",x->{});
	}
	
	@FXML
	public void onMenuItemConsultarAdotanteAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AdotanteConsultaView.fxml",x->{});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		telaPrincipal.setOnAction(event -> {
			onMenuAction();
		});
		
	}
	
}
