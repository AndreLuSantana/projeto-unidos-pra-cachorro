package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/loginView.fxml"));
			Parent parent = loader.load();
			
			Scene mainScene = new Scene(parent); 
			primaryStage.getIcons().add(new Image("/images/favicon.png"));
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("UNIDOS PRA CACHORRO");
			primaryStage.show();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
