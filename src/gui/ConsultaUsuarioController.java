package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.services.AnimalService;
import model.services.LoadViewService;
import model.services.Login;
import model.services.TelaPrincipal;

public class ConsultaUsuarioController implements Initializable {
	
	@FXML
	private Button btnConsultarUsuario;
	
	@FXML
	private Button btnCancelarConsultaUsuario;
	
	@FXML
	private TextField txtPesquisaUsuario;
	
	@FXML
	private Label txtErro;
	
	public TextField getTxtPesquisaUsuario() {
		return this.txtPesquisaUsuario;
	}

	
	public TextField getTxtPesquisaAnimal() {
		return this.txtPesquisaUsuario;
	}
	
	public Label getTxtErro() {
		return txtErro;
	}

	public void setTxtErro(Label txtErro) {
		this.txtErro = txtErro;
	}

	@FXML
	public void onBtnConsultarUsuarioAction() {
		
		
		LoadViewService lvs  = new LoadViewService();
			
		lvs.loadView("/gui/ResultadoConsultaUsuarioView.fxml", (ResultadoConsultaUsuarioController controller)->
		{
			controller.setUsuarioService(new AnimalService());
			
			if(txtPesquisaUsuario.getText().equals("")) {
				controller.atualizarTabela();
			}else {
				controller.localizaPorNome(txtPesquisaUsuario.getText());
				
			}
		});
		
	}
	@FXML
	public void onBtnCancelarConsultaUsuarioAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadViewMain("/gui/MainView.fxml");
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Constraints.SetTextFieldMaxLength(txtPesquisaUsuario, 50);
		
		btnConsultarUsuario.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarUsuarioAction();;
			}
		});
		
		btnCancelarConsultaUsuario.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnCancelarConsultaUsuarioAction();
			}
		});
		
		getTxtPesquisaUsuario().setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarAnimalAction();
			}
		});
	}
	
}
