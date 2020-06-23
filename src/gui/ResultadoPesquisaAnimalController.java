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
import model.services.LoadViewService;
import model.services.TelaPrincipal;

public class ResultadoPesquisaAnimalController implements Initializable{

	@FXML 
	private TableView<Animal> tableViewAnimal;
	@FXML 
	private TableColumn<Animal, Integer> tableViewID;
	@FXML 
	private TableColumn<Animal, Date> tableViewDate;
	@FXML 
	private TableColumn<Animal, String> tableViewCor;
	@FXML 
	private TableColumn<Animal, String> tableViewSexo;
	@FXML 
	private Button btnNovaConsulta;
	
	@FXML 
	private void onBtnNovaConsultaAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaAnimalView.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		initializeNodes();
	}

	private void initializeNodes() {
		
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("dataResgateAnimal"));
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("corAnimal"));
		tableViewID.setCellValueFactory(new PropertyValueFactory<>("sexoAnimal"));
		
		//Comando para fazer com que a tableView tenha o tamanho da janela e possa redimencionado junto com a janela.
		Stage stage = (Stage)TelaPrincipal.getMainScene().getWindow(); 
		tableViewAnimal.prefHeightProperty().bind(stage.heightProperty());
	}

}
