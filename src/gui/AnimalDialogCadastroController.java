package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import model.entities.Animal;
import model.services.AnimalService;

public class AnimalDialogCadastroController implements Initializable{ 

	private Animal entidade;
	private AnimalService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private Button onBtnAlterar;
	
	@FXML
	private Button onBtnExcluir;

	@FXML
	private Button onBtnVoltar;

	@FXML
	private Label idAnimal;

	@FXML
	private Label tamanhoAnimal;

	@FXML
	private Label pesoAnimal;

	@FXML
	private Label corAnimal;

	@FXML
	private Label dataResgateAnimal;

	@FXML
	private Label vacinaAnimal;

	@FXML
	private Label sexoAnimal;

	@FXML
	private Label animalPrenha;

	@FXML
	private Label animalDevolvidoRua;

	@FXML
	private Label animalLevadoCanil;

	@FXML
	private Label animalCastrado;

	@FXML
	private Label animalAdocao;

	@FXML
	private Label tratamentosAnimal;
	
	
	public void setEntidade(Animal entidade) {
		this.entidade = entidade;
	}
	
	public void setService(AnimalService service) {
		this.service = service;
	}
	
	public void subScribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	public void atualizarLabelAnimal() {
		

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
	
	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	
	public void onBtnActionExcluir(Integer id) {
		
		
		
		service.deleteById(id);
		notifyDataChangeListener();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		onBtnVoltar.setOnAction(event ->{
			Utils.currentStage(event).close();
		});
		
		onBtnExcluir.setOnAction(event -> {
			Utils.currentStage(event);
			if(event != null) {
				Optional <ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR EXCLUSÃO", "Você tem certeza que deseja excluir o animal do cadastro?");
				int id = Utils.tryParseToInt(idAnimal.getText());
				if(resultado.get() == ButtonType.OK) {
					if(service == null) {
						throw new IllegalStateException("Service is null");
					}
					try {
						service.deleteById(id);
						notifyDataChangeListener();
						Utils.currentStage(event).close();
					}
					catch(DbException e) {
						Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
					}
				}
			}
			
			
		});
	}

	





}
