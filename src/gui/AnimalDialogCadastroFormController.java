package gui;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Animal;
import model.services.AnimalService;

public class AnimalDialogCadastroFormController implements Initializable{

	
	private Animal entidade;
	private AnimalService service;
	private AnimalDialogCadastroController animalController;
	private ResultadoConsultaAnimalController controller;
	
	
	@FXML
	private Button onBtnSalvar;
	
	@FXML
	private Button onBtnCancelar;

	@FXML
	private Label idAnimal;

	@FXML
	private TextField tamanhoAnimal;

	@FXML
	private TextField pesoAnimal;

	@FXML
	private TextField corAnimal;

	@FXML
	private TextField dataResgateAnimal;

	@FXML
	private TextField vacinaAnimal;

	@FXML
	private TextField sexoAnimal;

	@FXML
	private TextField animalPrenha;

	@FXML
	private TextField animalDevolvidoRua;

	@FXML
	private TextField animalLevadoCanil;

	@FXML
	private TextField animalCastrado;

	@FXML
	private TextField animalAdocao;

	@FXML
	private TextField tratamentosAnimal;
	
	
	public void setEntidade(Animal entidade) {
		this.entidade = entidade;
	}
	
	public void setService(AnimalService service) {
		this.service = service;
	}
	
	public void setAnimalDialogCadastroController(AnimalDialogCadastroController animalController) {
		this.animalController = animalController;
	}
	
	public void setController(ResultadoConsultaAnimalController controller) {
		this.controller = controller;
	}
	
	public void atualizarFormAnimal() {
		

		idAnimal.setText(String.valueOf(entidade.getIdAnimal()));
		tamanhoAnimal.setText(String.valueOf(entidade.getTamanhoAnimal()));
		pesoAnimal.setText(String.valueOf(entidade.getPesoAnimal()));
		corAnimal.setText(entidade.getCorAnimal());
		dataResgateAnimal.setText(String.valueOf(entidade.getDataResgateAnimal()));
		vacinaAnimal.setText(entidade.getVacinasAnimal());
		sexoAnimal.setText(entidade.getSexoAnimal());
		animalPrenha.setText(entidade.getPrenhaAnimal());
		animalDevolvidoRua.setText(entidade.getDevolvidoParaRuaAnimal());
		animalLevadoCanil.setText(entidade.getLevadoCanilAnimal());
		animalCastrado.setText(entidade.getCastradoAnimal());
		animalAdocao.setText(entidade.getDispAdocaoAnimal());
		tratamentosAnimal.setText(entidade.getTratamentosAnimal());

	}
	
	public void atualizarAnimal(Animal obj) {
		
		entidade.setIdAnimal(Utils.tryParseToInt(idAnimal.getText()));
		entidade.setTamanhoAnimal(Utils.tryParseToDouble(tamanhoAnimal.getText()));
		entidade.setPesoAnimal(Utils.tryParseToDouble(pesoAnimal.getText()));
		entidade.setCorAnimal(corAnimal.getText());
		entidade.setDataResgateAnimal(dataResgateAnimal.getText());
		entidade.setVacinasAnimal(vacinaAnimal.getText());
		entidade.setSexoAnimal(sexoAnimal.getText());
		entidade.setPrenhaAnimal(animalPrenha.getText());
		entidade.setDevolvidoParaRuaAnimal(animalDevolvidoRua.getText());
		entidade.setLevadoCanilAnimal(animalLevadoCanil.getText());
		entidade.setCastradoAnimal(animalCastrado.getText());
		entidade.setDispAdocaoAnimal(animalAdocao.getText());
		entidade.setTratamentosAnimal(tratamentosAnimal.getText());
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		onBtnCancelar.setOnAction(event ->{
			Utils.currentStage(event).close();
		});
		
		onBtnSalvar.setOnAction(event -> {
			Utils.currentStage(event);
			if(event != null) {
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR ALTERAÇÃO", "Você tem certeza que deseja alterar os dados do animal?");
				
				if(resultado.get() == ButtonType.OK) {
					if(service == null) {
						throw new IllegalStateException("Service is null");
					}
					try {
						int id = Utils.tryParseToInt(idAnimal.getText());
						entidade = service.findByID(id);
						atualizarAnimal(entidade);
						service.insertOrUpdate(entidade);
						
						Alerts.showAlert("ALTERAÇÃO REALIZADA", null, "Cadastro alterado com sucesso!", AlertType.CONFIRMATION);
						Utils.currentStage(event).close();
						
					}
					catch(DbException e) {
						Alerts.showAlert("Error updating object", null, e.getMessage(), AlertType.ERROR);
					}
				}
			}
			
			
		});
	}
}
