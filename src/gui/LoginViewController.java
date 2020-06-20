package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.entities.Login;
import model.entities.TelaPrincipal;

public class LoginViewController implements Initializable {

	
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtEmailLogin;
	
	@FXML
	private PasswordField pwfSenhaLogin;
	
	@FXML
	private Button btnEntrarLogin;
	
		
	@FXML
	private void onBtnEntrarLoginAction() {
		login();
	}

	
	public void login() {
		
		if(DaoFactory.createUsuarioDao().checkLogin(txtEmailLogin.getText(), pwfSenhaLogin.getText()))
		{
					
			TelaPrincipal tp = new TelaPrincipal();
			tp.start(new Stage());
			
			Login.getStage().close();
			
		}else {
			lblStatus.setText("E-mail e/ou senha estão errados");
			txtEmailLogin.setText("");
			pwfSenhaLogin.setText("");
			txtEmailLogin.requestFocus();
		}
		
		
	}
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		btnEntrarLogin.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				login();
			}
		});
		
		pwfSenhaLogin.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				login();
			}
		});
		
	}

}
