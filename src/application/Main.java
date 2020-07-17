package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.services.Login;


public class Main extends Application {
	
	public void start(Stage primaryStage) {
		
		Login lg = new Login();
		lg.start(new Stage());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
