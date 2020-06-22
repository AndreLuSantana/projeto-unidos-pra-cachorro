package model.services;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application{

	private static Stage stage;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/loginView.fxml"));
			AnchorPane anchorPane = loader.load();
			
			Scene loginScene = new Scene(anchorPane); 
			primaryStage.getIcons().add(new Image("/images/favicon.png"));
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("UNIDOS PRA CACHORRO");
			primaryStage.show();
			setStage(primaryStage);	
		
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}


	public static Stage getStage() {
		return stage;
	}


	public static void setStage(Stage stage) {
		Login.stage = stage;
	}
	
	
}
