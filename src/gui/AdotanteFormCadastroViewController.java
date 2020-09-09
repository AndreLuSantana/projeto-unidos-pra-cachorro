package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Adotante;
import model.services.AdotanteService;
import model.services.LoadViewService;
import model.services.UsuarioService;

public class AdotanteFormCadastroViewController implements Initializable{
	
	
	private AdotanteService service = new AdotanteService();
	
	@FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField nomeAdotante;
    
    @FXML
    private TextField enderecoAdotante;
    
    @FXML
    private TextField telefoneAdotante;

    @FXML
    private TextField emailAdotante;
    
    @FXML
    private Label lblErro;
    
    private List<TextField> list = new ArrayList<>();
    
    public Adotante instanciarAdotante(Adotante obj) {
    	
    	obj.setNomeAdotante(nomeAdotante.getText());
    	obj.setEnderecoAdotante(enderecoAdotante.getText());
    	obj.setTelefoneAdotante(telefoneAdotante.getText());
    	obj.setEmailAdotante(emailAdotante.getText());
    	
    	
    	return obj;
    }
    
    public void textoErroLabel(Label label) {
    	label.setText("Faltam campos a serem preenchimentos");
    }
    
    public void adicioanarALista(TextField text) {
    	
    	if(text.getText().equals("")) {
    		list.add(text);
    	}
    }
    
    public void apagarCampos() {
    	
    	LoadViewService lvs  = new LoadViewService();
		lvs.loadView("/gui/AdotanteFormCadastroView.fxml",x->{});
    	
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Constraints.setTextFieldCaracter(nomeAdotante);
		//Constraints.setTextFieldTelefone(telefoneAdotante);
		
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
			
			adicioanarALista(nomeAdotante);
			adicioanarALista(enderecoAdotante);
			adicioanarALista(telefoneAdotante);
			adicioanarALista(emailAdotante);
			
			
			if(!(list.isEmpty())) {
				
				textoErroLabel(lblErro);
				list.removeAll(list);
				
			}else {
			
				if(event != null) {
					Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR CADASTRAMENTO", "Você tem certeza que deseja cadastrar este Adotante?");
					
					if(resultado.get() == ButtonType.OK) {
						
						Alerts.showAlert("CADASTRO EFETUADO", null, "Cadastro realizado com sucesso", AlertType.CONFIRMATION);
						
						Adotante adotante = new Adotante();
						instanciarAdotante(adotante);
						service.insertOrUpdate(adotante);
						apagarCampos();
						
					}else {
						
						Alerts.showAlert("CADASTRO CANCELADO", null, "Cadastramento Cancelado", AlertType.CONFIRMATION);
						apagarCampos();
					}
				}
			}
		});
		
	}

}
