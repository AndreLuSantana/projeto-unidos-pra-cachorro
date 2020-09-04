package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Adotante;
import model.entities.Animal;
import model.services.AdotanteService;
import model.services.AnimalService;

public class AdotanteDialogCadastroController implements Initializable, DataChangeListener{ 

	private Adotante entidade;
	private AdotanteService service;
	private AnimalService serviceAnimal;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private Button onBtnAlterar;
	
	@FXML
	private Button onBtnExcluir;

	@FXML
	private Button onBtnVoltar;

	@FXML
	private Label idAdotante;

	@FXML
	private Label nomeAdotante;

	@FXML
	private Label enderecoAdotante;
	
	@FXML
	private Label telefoneAdotante;
	
	@FXML
	private Label emailAdotante;
	
	@FXML
	private Label idAnimal;
	
	public void setEntidade(Adotante entidade) {
		this.entidade = entidade;
	}
	
	public void setService(AdotanteService service) {
		this.service = service;
	}
	
	public void setServiceAnimal(AnimalService serviceAnimal) {
		this.serviceAnimal = serviceAnimal;
	}
	
	public void subScribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	public void atualizarLabelAdotante() {
		

		idAdotante.setText(String.valueOf(entidade.getIdAdotante()));
		nomeAdotante.setText(entidade.getNomeAdotante());
		enderecoAdotante.setText(entidade.getEnderecoAdotante());
		telefoneAdotante.setText(entidade.getTelefoneAdotante());
		emailAdotante.setText(entidade.getEmailAdotante());
		
	}
	
	public void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		onBtnVoltar.setOnAction(event ->{
			notifyDataChangeListener();
			Utils.currentStage(event).close();
		});
		
		onBtnExcluir.setOnAction(event -> {
			Utils.currentStage(event);
			if(event != null) {
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR EXCLUSÃO", "Você tem certeza que deseja excluir o adotante do cadastro?");
				int id = Utils.tryParseToInt(idAdotante.getText());
				if(resultado.get() == ButtonType.OK) {
					if(service == null) {
						throw new IllegalStateException("Service is null");
					}
					try {
						service.deleteById(id);
						notifyDataChangeListener();
						Utils.currentStage(event).close();
					}
					catch(DbException e) {
						Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
					}
				}
			}
		});

		onBtnAlterar.setOnAction(event ->{
			Stage parentStage = Utils.currentStage(event);

			Integer id = Utils.tryParseToInt(idAdotante.getText());
			Adotante obj = service.findByID(id);
			
			createAdotanteDialogForm(obj, "/gui/AdotanteDialogCadastroFormView.fxml", parentStage);
			notifyDataChangeListener();
			parentStage.close();

		});
	}
	
	public void createAdotanteDialogForm(Adotante obj, String absoluteName, Stage parentStage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();	

			AdotanteDialogCadastroFormController controller = loader.getController();
			controller.setEntidade(obj);
			controller.setService(new AdotanteService());
			controller.atualizarFormAdotante();;


			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image("/images/favicon.png"));
			dialogStage.setTitle("Alterar Cadastro do Usuário");
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
		
		
	}
}
