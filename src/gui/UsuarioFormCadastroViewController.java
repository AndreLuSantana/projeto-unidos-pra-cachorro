package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.Usuario;
import model.services.LoadViewService;
import model.services.UsuarioService;

public class UsuarioFormCadastroViewController implements Initializable{
	
	
	private UsuarioService service = new UsuarioService();
	
	@FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField nomeUsuario;

    @FXML
    private TextField emailUsuario;

    @FXML
    private PasswordField senhaUsuario;
    
    @FXML
    private Label lblErroNome;
    
    @FXML
    private Label lblErroEmail;
    
    @FXML
    private Label lblErroSenha;
    
    @FXML
    private Label lblErro;
    
    private List<TextField> list = new ArrayList<>();
    
    public Usuario instanciarUsuario(Usuario obj) {
    	
    	obj.setNomeUsuario(nomeUsuario.getText());
    	obj.setEmailUsuario(emailUsuario.getText());
    	obj.setSenhaUsuario(senhaUsuario.getText());
    	
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
		lvs.loadView("/gui/UsuarioFormCadastroView.fxml",x->{});
    	
    }


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
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
			
			adicioanarALista(nomeUsuario);
			adicioanarALista(emailUsuario);
			adicioanarALista(senhaUsuario);
			
			if(!(list.isEmpty())) {
				
				textoErroLabel(lblErro);
				list.removeAll(list);
				
			}else {
			
				if(event != null) {
					Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR CADASTRAMENTO", "Você tem certeza que deseja cadastrar este Usuário?");
					
					if(resultado.get() == ButtonType.OK) {
						
						Alerts.showAlert("CADASTRO EFETUADO", null, "Cadastro realizado com sucesso", AlertType.CONFIRMATION);
						
						Usuario usuario = new Usuario();
						instanciarUsuario(usuario);
						service.insertOrUpdate(usuario);
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
