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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Animal;
import model.entities.Usuario;
import model.services.AnimalService;
import model.services.LoadViewService;
import model.services.TelaPrincipal;
import model.services.UsuarioService;

public class ResultadoConsultaUsuarioController implements Initializable, DataChangeListener{

	private UsuarioService service;
	private UsuarioDialogCadastroController controller;
	
	@FXML 
	private TableView<Usuario> tableViewUsuario;
	@FXML 
	private TableColumn<Usuario, Integer> columnID;
	@FXML 
	private TableColumn<Usuario, String> columnNome;
	@FXML 
	private TableColumn<Usuario, String> columnEmail;
	
	
	@FXML 
	private Button btnNovaConsulta;
	
	private	ObservableList<Usuario> obsList;
	
	public void setUsuarioService(UsuarioService service) {
		this.service = service;
	}
	
	public void setUsuarioDialogCadastroController(UsuarioDialogCadastroController controller) {
		this.controller = controller;
	}
	
	public TableView<Usuario> getTableViewUsuario() {
		return tableViewUsuario;
	}


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
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("emailUsuario"));
		
		//Comando para fazer com que a tableView tenha o tamanho da janela e possa redimencionado junto com a janela.
		Stage stage = (Stage)TelaPrincipal.getMainScene().getWindow(); 
		tableViewUsuario.prefHeightProperty().bind(stage.heightProperty());
		
		tableViewUsuario.setOnMouseClicked(event ->{
			Stage parentStage = Utils.currentStageMouse(event);
			
			/*if(event.getClickCount() == 2) {

				String nome = tableViewUsuario.getSelectionModel().getSelectedItem().getNomeUsuario();
				List<Usuario> obj = service.findByName(nome);

				createUsuarioDialog(obj, "/gui/UsuarioDialogCadastroView.fxml", parentStage);

			}*/
		});
	}
	
	public void atualizarTabela() {
		
		if(service == null) {
			throw new IllegalStateException("Serviço não está sendo executado.");
		}
		
		List<Usuario> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);
			
	}
	
	public void localizaPorNome(String txtField) {
		
		List<Usuario> list = service.findByName(txtField);
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);
		
		if(list == null){
			LoadViewService lvs  = new LoadViewService();
			lvs.loadView("/gui/ConsultaUsuarioView.fxml", (ConsultaUsuarioController controller)->{
				controller.getTxtErro().setText("* Nenhum usuário encontrado com este ID");
				controller.getTxtPesquisaAnimal().setText("");
				
			});
		}
	}
	
	public void createUsuarioDialog(Usuario obj, String absoluteName, Stage parentStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();	
			
			UsuarioDialogCadastroController controller = loader.getController();
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
