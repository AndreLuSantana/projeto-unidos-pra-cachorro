package gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.entities.Animal;
import model.services.AnimalService;
import model.services.LoadViewService;

public class AnimalFormCadastroViewController implements Initializable{
	
	private Animal entidade;
	private AnimalService service;
	

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField corAnimal;

    @FXML
    private TextField vacinasAnimal;

    @FXML
    private CheckBox sexoMacho;

    @FXML
    private CheckBox sexoFemea;

    @FXML
    private CheckBox prenhaSim;

    @FXML
    private CheckBox prenhaNao;

    @FXML
    private CheckBox devolvidoSim;

    @FXML
    private CheckBox devolvidoNao;

    @FXML
    private CheckBox canilSim;

    @FXML
    private CheckBox canilNao;

    @FXML
    private CheckBox castradoSim;

    @FXML
    private CheckBox castradoNao;

    @FXML
    private CheckBox adocaoSim;

    @FXML
    private CheckBox adocaoNao;

    @FXML
    private DatePicker dataResgasteAnimal;

    @FXML
    private TextField tamanhoAnimal;

    @FXML
    private TextField pesoAnimal;

    @FXML
    private TextArea tratamentoAnimal;
    
    @FXML
    private Label lblSexoAnimal;
    
    @FXML
    private Label lblPrenhaAnimal;
    
    @FXML
    private Label lblDevolvidoRuaAnimal;
    
    @FXML
    private Label lblLevadoCanilAnimal;
    
    @FXML
    private Label lblCastradoAnimal;
    
    @FXML
    private Label lblDisponivelAdocaoAnimal;
    
    public void setEntidade(Animal entidade) {
    	entidade = this.entidade;
    }
    
    public void setService(AnimalService service) {
    	service = this.service;
    }
    
    
    public void verificarDuplicidadeSelecao(CheckBox box1, CheckBox box2, Label label) {
    	
    	box1.setOnMouseClicked(event ->{
    		if(box1.isSelected() && box2.isSelected()) {
        		box1.setSelected(true);
        		box2.setSelected(false);
    		}
    	}
    	);
    	
    	box2.setOnMouseClicked(event ->{
    		if(box1.isSelected() && box2.isSelected()) {
        		box1.setSelected(false);
        		box2.setSelected(true);
    		}
    	}
    	);
    }
    
    public void apagarCampos() {
    	
    	LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AnimalFormCadastroView.fxml",x->{});
    	
    }


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		setService(new AnimalService());
		
		verificarDuplicidadeSelecao(sexoMacho, sexoFemea, lblSexoAnimal);
		verificarDuplicidadeSelecao(prenhaSim, prenhaNao, lblPrenhaAnimal);
		verificarDuplicidadeSelecao(devolvidoSim, devolvidoNao, lblDevolvidoRuaAnimal);
		verificarDuplicidadeSelecao(canilSim, canilNao, lblLevadoCanilAnimal);
		verificarDuplicidadeSelecao(castradoSim, castradoNao, lblCastradoAnimal);
		verificarDuplicidadeSelecao(adocaoSim, adocaoNao, lblDisponivelAdocaoAnimal);
		
		btnCancelar.setOnAction(event -> {
			Utils.currentStage(event);
			
			if(event != null) {
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR CANCELAMENTO", "Você tem certeza que deseja cancelar?");
				if(resultado.get() == ButtonType.OK) {
					
					Alerts.showAlert("CADASTRO CANCELADO", null, "Cancelamento efetuado com sucesso", AlertType.CONFIRMATION);
					apagarCampos();
				}
			}
		});
			
		btnSalvar.setOnAction(event -> {
			Utils.currentStage(event);
			
			
		});
		
	}

}
