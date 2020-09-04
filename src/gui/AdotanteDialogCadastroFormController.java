package gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Animal;
import model.entities.Adotante;
import model.services.AnimalService;
import model.services.AdotanteService;

public class AdotanteDialogCadastroFormController implements Initializable{

	
	private Adotante entidade;
	private AdotanteService service;
	private AdotanteDialogCadastroController usuarioController;
	private AdotanteResultadoConsultaController controller;
	
	
	@FXML
	private Button onBtnSalvar;
	
	@FXML
	private Button onBtnCancelar;

	@FXML
	private Label idAdotante;

	@FXML
	private TextField nomeAdotante;
	
	@FXML
	private TextField enderecoAdotante;

	@FXML
	private TextField telefoneAdotante;
	
	@FXML
	private TextField emailAdotante;
	
	@FXML
	private Label idAnimal;

	
	public void setEntidade(Adotante entidade) {
		this.entidade = entidade;
	}
	
	public void setService(AdotanteService service) {
		this.service = service;
	}
	
	public void setAdotanteDialogCadastroController(AdotanteDialogCadastroController usuarioController) {
		this.usuarioController = usuarioController;
	}
	
	public void setController(AdotanteResultadoConsultaController controller) {
		this.controller = controller;
	}
	
	public void atualizarFormAdotante() {
		
		idAdotante.setText(String.valueOf(entidade.getIdAdotante()));
		nomeAdotante.setText(entidade.getNomeAdotante());
		enderecoAdotante.setText(entidade.getEnderecoAdotante());
		telefoneAdotante.setText(entidade.getTelefoneAdotante());
		emailAdotante.setText(entidade.getEmailAdotante());
		
		
	}
	
	public void atualizarAdotante(Adotante obj) {
		
		entidade.setIdAdotante(Utils.tryParseToInt(idAdotante.getText()));
		entidade.setNomeAdotante(nomeAdotante.getText());
		entidade.setEnderecoAdotante(enderecoAdotante.getText());
		entidade.setTelefoneAdotante(telefoneAdotante.getText());
		entidade.setEmailAdotante(emailAdotante.getText());
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		onBtnCancelar.setOnAction(event ->{
			Utils.currentStage(event).close();
		});
		
		onBtnSalvar.setOnAction(event -> {
			Utils.currentStage(event);
			if(event != null) {
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR ALTERAÇÃO", "Você tem certeza que deseja alterar os dados do adotante?");
				
				if(resultado.get() == ButtonType.OK) {
					if(service == null) {
						throw new IllegalStateException("Service is null");
					}
					try {
						int id = Utils.tryParseToInt(idAdotante.getText());
						entidade = service.findByID(id);
						atualizarAdotante(entidade);
						service.insertOrUpdate(entidade);
						
						Alerts.showAlert("ALTERAÇÃO REALIZADA", null, "Cadastro alterado com sucesso!", AlertType.CONFIRMATION);
						Utils.currentStage(event).close();
						
						
					}
					catch(DbException e) {
						Alerts.showAlert("Error updating object", null, e.getMessage(), AlertType.ERROR);
					}
				}
			}
			
			
		});
	}
}
