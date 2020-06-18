package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	
	public LoginViewController() {
		
	}
		
	@FXML
	protected void onBtnEntrarLoginAction() {
		
		if(DaoFactory.createUsuarioDao().checkLogin(txtEmailLogin.getText(), pwfSenhaLogin.getText()))
		{
					
			TelaPrincipal tp = new TelaPrincipal();
			tp.start(new Stage());
			
			
		}else {
			lblStatus.setText("E-mail e/ou senha estão errados");
			txtEmailLogin.setText("");
			pwfSenhaLogin.setText("");
			txtEmailLogin.requestFocus();
		}
		
		Login.getStage().close();
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

}
