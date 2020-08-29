package gui;

import java.io.IOException;
import java.net.URL;
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
import model.entities.Adotante;
import model.services.LoadViewService;
import model.services.TelaPrincipal;
import model.services.AdotanteService;

public class AdotanteResultadoConsultaController implements Initializable, DataChangeListener{

	private AdotanteService service;
	private AdotanteDialogCadastroController controller;
	
	@FXML 
	private TableView<Adotante> tableViewAdotante;
	@FXML 
	private TableColumn<Adotante, Integer> columnID;
	@FXML 
	private TableColumn<Adotante, String> columnNome;
	@FXML 
	private TableColumn<Adotante, String> columnEmail;
	
	
	@FXML 
	private Button btnNovaConsulta;
	
	private	ObservableList<Adotante> obsList;
	
	public void setAdotanteService(AdotanteService service) {
		this.service = service;
	}
	
	public void setAdotanteDialogCadastroController(AdotanteDialogCadastroController controller) {
		this.controller = controller;
	}
	
	public TableView<Adotante> getTableViewAdotante() {
		return tableViewAdotante;
	}


	@FXML 
	private void onBtnNovaConsultaAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AdotanteConsultaView.fxml",x->{});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("idAdotante"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeAdotante"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("emailAdotante"));
		
		//Comando para fazer com que a tableView tenha o tamanho da janela e possa redimencionado junto com a janela.
		Stage stage = (Stage)TelaPrincipal.getMainScene().getWindow(); 
		tableViewAdotante.prefHeightProperty().bind(stage.heightProperty());
		
		tableViewAdotante.setOnMouseClicked(event ->{
			Stage parentStage = Utils.currentStageMouse(event);
			
			if(event.getClickCount() == 2) {

				int id = tableViewAdotante.getSelectionModel().getSelectedItem().getIdAdotante();
				Adotante obj = service.findByID(id);

				createAdotanteDialog(obj, "/gui/AdotanteDialogCadastroView.fxml", parentStage);

			}
		});
	}
	
	public void atualizarTabela() {
		
		if(service == null) {
			throw new IllegalStateException("Serviço não está sendo executado.");
		}
		
		List<Adotante> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewAdotante.setItems(obsList);
			
	}
	
	public void localizaPorNome(String txtField) {
		
		List<Adotante> list = service.findByName(txtField);
		obsList = FXCollections.observableArrayList(list);
		tableViewAdotante.setItems(obsList);
		
		if(list == null){
			LoadViewService lvs  = new LoadViewService();
			lvs.loadView("/gui/AdotanteConsultaView.fxml", (AdotanteConsultaController controller)->{
				controller.getTxtErro().setText("* Nenhum usuário encontrado com este nome");
				controller.getTxtPesquisaAdotante().setText("");
				
			});
		}
	}
	
	public void createAdotanteDialog(Adotante obj, String absoluteName, Stage parentStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();	
			
			AdotanteDialogCadastroController controller = loader.getController();
			controller.setEntidade(obj);
			controller.setService(new AdotanteService());
			controller.subScribeDataChangeListener(this);
			controller.atualizarLabelAdotante();
			
			
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/images/favicon.png"));
			dialogStage.setTitle("Cadastro do Adotante");
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
