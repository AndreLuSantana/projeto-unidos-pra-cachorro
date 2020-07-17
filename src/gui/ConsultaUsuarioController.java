package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.dao.DaoFactory;
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
	
	public TextField getTxtPesquisaUsuario() {
		return this.txtPesquisaUsuario;
	}

	@FXML
	public void onBtnConsultarUsuarioAction() {
		LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/ResultadoConsultaUsuarioView.fxml", x->{});
	}
	
	@FXML
	public void onBtnCancelarConsultaUsuarioAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadViewMain("/gui/MainView.fxml");
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		btnConsultarUsuario.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarUsuarioAction();
			}
		});
		
		btnCancelarConsultaUsuario.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnCancelarConsultaUsuarioAction();
			}
		});
		
		txtPesquisaUsuario.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarUsuarioAction();
			}
		});
	}
	
}
