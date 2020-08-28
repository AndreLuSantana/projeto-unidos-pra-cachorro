package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Animal;
import model.services.AnimalService;
import model.services.LoadViewService;
import model.services.TelaPrincipal;

public class AnimalResultadoConsultaController implements Initializable, DataChangeListener{

	private AnimalService service;
	private AnimalDialogCadastroController controller;
	
	@FXML 
	private TableView<Animal> tableViewAnimal;
	@FXML 
	private TableColumn<Animal, Integer> columnID;
	@FXML 
	private TableColumn<Animal, String> columnCor;
	@FXML 
	private TableColumn<Animal, String> columnSexo;
	@FXML 
	private TableColumn<Animal, Date> columnDate;
	
	@FXML 
	private Button btnNovaConsulta;
	
	private	ObservableList<Animal> obsList;
	
	public void setAnimalService(AnimalService service) {
		this.service = service;
	}
	
	public void setAnimalDialogCadastroController(AnimalDialogCadastroController controller) {
		this.controller = controller;
	}
	
	public TableView<Animal> getTableViewAnimal() {
		return tableViewAnimal;
	}


	@FXML 
	private void onBtnNovaConsultaAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ConsultaAnimalView.fxml",x->{});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
		columnCor.setCellValueFactory(new PropertyValueFactory<>("corAnimal"));
		columnSexo.setCellValueFactory(new PropertyValueFactory<>("sexoAnimal"));
		columnDate.setCellValueFactory(new PropertyValueFactory<>("dataResgateAnimal"));
		
		//Comando para fazer com que a tableView tenha o tamanho da janela e possa redimencionado junto com a janela.
		Stage stage = (Stage)TelaPrincipal.getMainScene().getWindow(); 
		tableViewAnimal.prefHeightProperty().bind(stage.heightProperty());
		
		tableViewAnimal.setOnMouseClicked(event ->{
			Stage parentStage = Utils.currentStageMouse(event);
			
			if(event.getClickCount() == 2) {

				Integer id = tableViewAnimal.getSelectionModel().getSelectedItem().getIdAnimal();
				Animal obj = service.findByID(id);

				createAnimalDialog(obj, "/gui/AnimalDialogCadastroView.fxml", parentStage);

			}
		});
	}
	
	public void atualizarTabela() {
		
		if(service == null) {
			throw new IllegalStateException("Serviço não está sendo executado.");
		}
		
		List<Animal> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewAnimal.setItems(obsList);
			
	}
	
	public void localizaPorId(String txtField) {
		
		Animal obj = service.findByID(Utils.tryParseToInt(txtField));
		if(obj != null) {
			obsList = FXCollections.observableArrayList(obj);
			tableViewAnimal.setItems(obsList);
		}else{
			LoadViewService lvs  = new LoadViewService();
			lvs.loadView("/gui/AnimalConsultaView.fxml", (AnimalConsultaController controller)->{
				controller.getTxtErro().setText("* Nenhum animal encontrado com este ID");
				controller.getTxtPesquisaAnimal().setText("");
				
			});
		}
	}
	
	public void createAnimalDialog(Animal obj, String absoluteName, Stage parentStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();	
			
			AnimalDialogCadastroController controller = loader.getController();
			controller.setEntidade(obj);
			controller.setService(new AnimalService());
			controller.subScribeDataChangeListener(this);
			controller.atualizarLabelAnimal();
			
			
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/images/favicon.png"));
			dialogStage.setTitle("Cadastro do Animal");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
		}
		
		
	}

	@Override
	public void onDataChanged() {
		 atualizarTabela();
		
	}
}
