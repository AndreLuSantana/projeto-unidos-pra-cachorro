package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.entities.TelaPrincipal;

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
		
	}
	
	@FXML
	public void onMenuItemCadastrarUsuarioAction() {
		
	}
	
	@FXML
	public void onMenuItemCadastrarAdotanteAction() {
		
	}
	
	@FXML
	public void onMenuItemConsultarAnimalAction() {
		
		loadView("/gui/ConsultaAnimalView.fxml");
	}
	
	@FXML
	public void onMenuItemConsultarUsuarioAction() {
		
	}
	
	@FXML
	public void onMenuItemConsultarAdotanteAction() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized void loadView(String absoluteName) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = TelaPrincipal.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
}
