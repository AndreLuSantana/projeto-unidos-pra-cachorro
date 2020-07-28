package gui.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Alerts {

	public static void showAlert (String title, String header, String content, AlertType type) {
		
		Alert alert = new Alert(type);
		Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/images/favicon.png"));
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		
	}
	
	public static Optional<ButtonType> mostrarConfirmacao(String titulo, String conteudo){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/images/favicon.png"));
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(conteudo);
		return alert.showAndWait();
	}
}

