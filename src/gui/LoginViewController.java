package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
		
	public LoginViewController(TextField txtEmailLogin, PasswordField pwfSenhaLogin, Button btnEntrarLogin) {
		super();
		this.txtEmailLogin = txtEmailLogin;
		this.pwfSenhaLogin = pwfSenhaLogin;
		this.btnEntrarLogin = btnEntrarLogin;
	}

	
	@FXML
	public void onBtnEntrarLoginAction() {
		if(txtEmailLogin.getText().equals("andre") && pwfSenhaLogin.getText().equals("123")) {
			
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollpane = loader.load();
			
			scrollpane.setFitToHeight(true);
			scrollpane.setFitToWidth(true);
			
			Stage primaryStage = new Stage();
			Scene mainScene = new Scene(scrollpane); 
			primaryStage.getIcons().add(new Image("/images/favicon.png"));
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("UNIDOS PRA CACHORRO");
			primaryStage.show();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			lblStatus.setText("E-mail e/ou senha estão errados");
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

}
