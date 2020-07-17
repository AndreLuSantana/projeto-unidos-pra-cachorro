package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Animal;
import model.services.AnimalService;
import model.services.LoadViewService;
import model.services.TelaPrincipal;

public class ResultadoConsultaAdotanteController implements Initializable{

	
	@FXML 
	private TableView<Animal> tableViewAnimal;
	@FXML 
	private TableColumn<Animal, Integer> columnID;
	@FXML 
	private TableColumn<Animal, Date> columnDate;
	@FXML 
	private TableColumn<Animal, String> columnCor;
	@FXML 
	private TableColumn<Animal, String> columnSexo;
	@FXML 
	private Button btnNovaConsulta;
	
	private	ObservableList<Animal> obsList;
	
	
	@FXML 
	private void onBtnNovaConsultaAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaAnimalView.fxml",x->{});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

	private void initializeNodes() {
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
		columnDate.setCellValueFactory(new PropertyValueFactory<>("dataResgateAnimal"));
		columnCor.setCellValueFactory(new PropertyValueFactory<>("corAnimal"));
		columnSexo.setCellValueFactory(new PropertyValueFactory<>("sexoAnimal"));
		
		//Comando para fazer com que a tableView tenha o tamanho da janela e possa redimencionado junto com a janela.
		Stage stage = (Stage)TelaPrincipal.getMainScene().getWindow(); 
		tableViewAnimal.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void atualizarTabela() {
		
		initializeNodes();
		
		
		/*List<Adotante> list = service.findTableView();
		obsList = FXCollections.observableArrayList(list);
		tableViewAnimal.setItems(obsList);*/
		
	}
}
