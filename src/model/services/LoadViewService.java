package model.services;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadViewService {
	
	private static Stage stage;
	private static Scene mainScene;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		LoadViewService.stage = stage;
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public synchronized void loadView(String absoluteName) {
			
			try {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				VBox newVBox = loader.load();
				
				mainScene = TelaPrincipal.getMainScene();
				VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
				
				Node mainMenu = mainVBox.getChildren().get(0);
				mainVBox.getChildren().clear();
				mainVBox.getChildren().add(mainMenu);
				mainVBox.getChildren().addAll(newVBox.getChildren());
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		}

	public synchronized void loadViewMain(String absoluteName) {
		
		TelaPrincipal.getStage().close();
		
		TelaPrincipal tp = new TelaPrincipal();
		tp.start(new Stage());
			
			
	}
	
}

