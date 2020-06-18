package model.entities;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaPrincipal extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

public void start(Stage primaryStage) {
		
		
		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
				ScrollPane scrollpane = loader.load();
				
				scrollpane.setFitToHeight(true);
				scrollpane.setFitToWidth(true);
				
				Stage mainStage = new Stage();
				Scene mainScene = new Scene(scrollpane); 
				primaryStage.getIcons().add(new Image("/images/favicon.png"));
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("UNIDOS PRA CACHORRO");
				primaryStage.show();
			
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
	
}
