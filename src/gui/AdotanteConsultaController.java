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
import model.services.LoadViewService;
import model.services.AdotanteService;

public class AdotanteConsultaController implements Initializable {
	
	@FXML
	private Button btnConsultarAdotante;
	
	@FXML
	private Button btnCancelarConsultaAdotante;
	
	@FXML
	private TextField txtPesquisaAdotante;
	
	@FXML
	private Label txtErro;
	
	public TextField getTxtPesquisaAdotante() {
		return this.txtPesquisaAdotante;
	}

	
	public Label getTxtErro() {
		return txtErro;
	}

	public void setTxtErro(Label txtErro) {
		this.txtErro = txtErro;
	}

	@FXML
	public void onBtnConsultarAdotanteAction() {
		
		
		LoadViewService lvs  = new LoadViewService();
			
		lvs.loadView("/gui/AdotanteResultadoConsultaView.fxml", (AdotanteResultadoConsultaController controller)->
		{
			controller.setAdotanteService(new AdotanteService());
			
			if(txtPesquisaAdotante.getText().equals("")) {
				controller.atualizarTabela();
			}else {
				controller.localizaPorNome(txtPesquisaAdotante.getText());
				
			}
		});
		
	}
	@FXML
	public void onBtnCancelarConsultaAdotanteAction() {
		
		LoadViewService lvs  = new LoadViewService();
		lvs.loadViewMain("/gui/MainView.fxml");
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Constraints.SetTextFieldMaxLength(txtPesquisaAdotante, 50);
		
		btnConsultarAdotante.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarAdotanteAction();;
			}
		});
		
		btnCancelarConsultaAdotante.setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnCancelarConsultaAdotanteAction();
			}
		});
		
		getTxtPesquisaAdotante().setOnKeyPressed((KeyEvent e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				onBtnConsultarAdotanteAction();
			}
		});
	}
	
}
