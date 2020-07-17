package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Animal;
import model.entities.Usuario;
import model.services.LoadViewService;
import model.services.TelaPrincipal;

public class ResultadoConsultaUsuarioController implements Initializable{

	@FXML 
	private TableView<Usuario> tableViewAnimal;
	@FXML 
	private TableColumn<Usuario, Integer> tableViewID;
	@FXML 
	private TableColumn<Usuario, String> tableViewNome;
	@FXML 
	private TableColumn<Usuario, String> tableViewSenha;
	@FXML 
	private TableColumn<Usuario, String> tableViewEmail;
	@FXML 
	private Button btnNovaConsulta;
	
	@FXML 
	private void onBtnNovaConsultaAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaUsuarioView.fxml",x->{});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		initializeNodes();
	}

	private void initializeNodes() {
		
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("senhaUsuario"));
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("emailUsuario"));
		
		//Comando para fazer com que a tableView tenha o tamanho da janela e possa redimencionado junto com a janela.
		Stage stage = (Stage)TelaPrincipal.getMainScene().getWindow(); 
		tableViewAnimal.prefHeightProperty().bind(stage.heightProperty());
	}

}
