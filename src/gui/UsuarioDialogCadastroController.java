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
import model.entities.Usuario;
import model.services.UsuarioService;

public class UsuarioDialogCadastroController implements Initializable, DataChangeListener{ 

	private Usuario entidade;
	private UsuarioService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private Button onBtnAlterar;
	
	@FXML
	private Button onBtnExcluir;

	@FXML
	private Button onBtnVoltar;

	@FXML
	private Label idUsuario;

	@FXML
	private Label nomeUsuario;

	@FXML
	private Label emailUsuario;
	
	@FXML
	private Label senhaUsuario;
	
	public void setEntidade(Usuario entidade) {
		this.entidade = entidade;
	}
	
	public void setService(UsuarioService service) {
		this.service = service;
	}
	
	public void subScribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	public void atualizarLabelUsuario() {
		

		idUsuario.setText(String.valueOf(entidade.getIdUsuario()));
		nomeUsuario.setText(String.valueOf(entidade.getNomeUsuario()));
		emailUsuario.setText(String.valueOf(entidade.getEmailUsuario()));
		senhaUsuario.setText(String.valueOf(entidade.getSenhaUsuario()));

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
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR EXCLUSÃO", "Você tem certeza que deseja excluir o usuário do cadastro?");
				int id = Utils.tryParseToInt(idUsuario.getText());
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

			Integer id = Utils.tryParseToInt(idUsuario.getText());
			Usuario obj = service.findByID(id);
			
			createUsuarioDialogForm(obj, "/gui/UsuarioDialogCadastroFormView.fxml", parentStage);
			notifyDataChangeListener();
			parentStage.close();

		});
	}
	
	public void createUsuarioDialogForm(Usuario obj, String absoluteName, Stage parentStage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();	

			UsuarioDialogCadastroFormController controller = loader.getController();
			controller.setEntidade(obj);
			controller.setService(new UsuarioService());
			controller.atualizarFormUsuario();;


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
