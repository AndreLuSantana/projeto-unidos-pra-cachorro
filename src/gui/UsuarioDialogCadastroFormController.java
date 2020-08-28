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
import model.entities.Usuario;
import model.services.AnimalService;
import model.services.UsuarioService;

public class UsuarioDialogCadastroFormController implements Initializable{

	
	private Usuario entidade;
	private UsuarioService service;
	private UsuarioDialogCadastroController usuarioController;
	private UsuarioResultadoConsultaController controller;
	
	
	@FXML
	private Button onBtnSalvar;
	
	@FXML
	private Button onBtnCancelar;

	@FXML
	private Label idUsuario;

	@FXML
	private TextField nomeUsuario;

	@FXML
	private TextField emailUsuario;
	
	@FXML
	private TextField senhaUsuario;
	
	public void setEntidade(Usuario entidade) {
		this.entidade = entidade;
	}
	
	public void setService(UsuarioService service) {
		this.service = service;
	}
	
	public void setUsuarioDialogCadastroController(UsuarioDialogCadastroController usuarioController) {
		this.usuarioController = usuarioController;
	}
	
	public void setController(UsuarioResultadoConsultaController controller) {
		this.controller = controller;
	}
	
	public void atualizarFormUsuario() {
		

		idUsuario.setText(String.valueOf(entidade.getIdUsuario()));
		nomeUsuario.setText(String.valueOf(entidade.getNomeUsuario()));
		senhaUsuario.setText(String.valueOf(entidade.getSenhaUsuario()));
		emailUsuario.setText(String.valueOf(entidade.getEmailUsuario()));
		
		
	}
	
	public void atualizarUsuario(Usuario obj) {
		
		entidade.setIdUsuario(Utils.tryParseToInt(idUsuario.getText()));
		entidade.setNomeUsuario(nomeUsuario.getText());
		entidade.setSenhaUsuario(senhaUsuario.getText());
		entidade.setEmailUsuario(emailUsuario.getText());
		
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		onBtnCancelar.setOnAction(event ->{
			Utils.currentStage(event).close();
		});
		
		onBtnSalvar.setOnAction(event -> {
			Utils.currentStage(event);
			if(event != null) {
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR ALTERAÇÃO", "Você tem certeza que deseja alterar os dados do usuário?");
				
				if(resultado.get() == ButtonType.OK) {
					if(service == null) {
						throw new IllegalStateException("Service is null");
					}
					try {
						int id = Utils.tryParseToInt(idUsuario.getText());
						entidade = service.findByID(id);
						atualizarUsuario(entidade);
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
