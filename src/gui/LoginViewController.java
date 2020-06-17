package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.dao.impl.UsuarioDaoJDBC;

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
	public void onBtnEntrarLoginAction() {
	
		UsuarioDaoJDBC usuarioDao = new UsuarioDaoJDBC(DB.getConnection());
		
		if(usuarioDao.checkLogin(txtEmailLogin.getText(), pwfSenhaLogin.getText())) {
			
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
				
				e.printStackTrace();
			}
			
		}else {
			lblStatus.setText("E-mail e/ou senha estão errados");
			txtEmailLogin.setText("");
			pwfSenhaLogin.setText("");
			txtEmailLogin.requestFocus();
		}
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

}
