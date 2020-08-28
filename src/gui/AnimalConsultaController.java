package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.services.AnimalService;
import model.services.LoadViewService;

public class AnimalConsultaController implements Initializable {

	
	
	@FXML
	private Button btnConsultarAnimal;
	
	@FXML
	private Button btnCancelarConsultaAnimal;
	
	@FXML
	private TextField txtPesquisaAnimal;
	
	@FXML
	private Label txtErro;
	
	public TextField getTxtPesquisaAnimal() {
		return this.txtPesquisaAnimal;
	}
	
	public Label getTxtErro() {
		return txtErro;
	}

	public void setTxtErro(Label txtErro) {
		this.txtErro = txtErro;
	}



	@FXML
	public void onBtnConsultarAnimalAction() {
		
		
		LoadViewService lvs  = new LoadViewService();
			
		lvs.loadView("/gui/AnimalResultadoConsultaView.fxml", (AnimalResultadoConsultaController controller)->
		{
			controller.setAnimalService(new AnimalService());
			
			if(txtPesquisaAnimal.getText().equals("")) {
				controller.atualizarTabela();
			}else {
				controller.localizaPorId(txtPesquisaAnimal.getText());
				
			}
		});
		
	}
	@FXML
	public void onBtnCancelarConsultaAnimalAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadViewMain("/gui/MainView.fxml");
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Constraints.setTextFieldInteger(txtPesquisaAnimal);
		
		btnConsultarAnimal.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarAnimalAction();
			}
		});
		
		btnCancelarConsultaAnimal.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnCancelarConsultaAnimalAction();
			}
		});
		
		txtPesquisaAnimal.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarAnimalAction();
			}
		});
	}
	
}
