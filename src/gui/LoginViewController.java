package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

	@FXML
	private TextField txtEmailLogin;
	
	@FXML
	private PasswordField pwfSenhaLogin;
	
	@FXML
	private Button btnEntrarLogin;
	
	public LoginViewController() {
		
	}
		
	public LoginViewController(TextField txtEmailLogin, PasswordField pwfSenhaLogin, Button btnEntrarLogin) {
		super();
		this.txtEmailLogin = txtEmailLogin;
		this.pwfSenhaLogin = pwfSenhaLogin;
		this.btnEntrarLogin = btnEntrarLogin;
	}



	public TextField getTxtEmailLogin() {
		return txtEmailLogin;
	}

	public void setTxtEmailLogin(TextField txtEmailLogin) {
		this.txtEmailLogin = txtEmailLogin;
	}

	public PasswordField getPwfSenhaLogin() {
		return pwfSenhaLogin;
	}

	public void setPwfSenhaLogin(PasswordField pwfSenhaLogin) {
		this.pwfSenhaLogin = pwfSenhaLogin;
	}
	
	@FXML
	public void onBtnEntrarLoginAction() {
		System.out.println("Funcionou");
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

}
