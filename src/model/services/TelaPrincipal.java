package model.services;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaPrincipal extends Application{
	
	private static Stage Stage;
	private static Scene mainScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
			
			
			try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
					ScrollPane scrollpane = loader.load();
					
					scrollpane.setId("logo");
					
					scrollpane.setFitToHeight(true);
					scrollpane.setFitToWidth(true);
					
					mainScene = new Scene(scrollpane); 
					//mainScene.getStylesheets().addAll(this.getClass().getResource("logo.css").toExternalForm());
					primaryStage.getIcons().add(new Image("/images/favicon.png"));
					primaryStage.setScene(mainScene);
					primaryStage.setTitle("UNIDOS PRA CACHORRO");
					primaryStage.setMaximized(true);
					primaryStage.show();
					setStage(primaryStage);	
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}


	public static Scene getMainScene() {
		return mainScene;
	}

	public static Stage getStage() {
		return Stage;
	}

	public static void setStage(Stage stage) {
		Stage = stage;
	}
	
	
	
}
