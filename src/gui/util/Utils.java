package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {

		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Stage currentStageMouse(MouseEvent event) {


		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Integer tryParseToInt(String str) {
		return Integer.parseInt(str);
	}

	public static Double tryParseToDouble(String str) {
		return Double.parseDouble(str);
	}

	
	
}
