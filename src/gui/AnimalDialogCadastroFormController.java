package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Animal;
import model.services.AnimalService;

public class AnimalDialogCadastroFormController implements Initializable{

	
	private Animal entidade;
	private AnimalService service;
	private AnimalDialogCadastroController animalController;
	private AnimalResultadoConsultaController controller;
	
	
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
	private TextField vacinaAnimal;

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
	
	public void setController(AnimalResultadoConsultaController controller) {
		this.controller = controller;
	}
	
	public void atualizarFormAnimal() {
		

		idAnimal.setText(String.valueOf(entidade.getIdAnimal()));
		tamanhoAnimal.setText(String.valueOf(entidade.getTamanhoAnimal()));
		pesoAnimal.setText(String.valueOf(entidade.getPesoAnimal()));
		corAnimal.setText(entidade.getCorAnimal());
		dataResgasteAnimal.setValue(atualizarDataFormulário(entidade.getDataResgateAnimal()));
		vacinaAnimal.setText(entidade.getVacinasAnimal());
		sexoMacho.setSelected(getCheckBoxSelecionado(entidade.getSexoAnimal(), "Macho"));
		sexoFemea.setSelected(getCheckBoxSelecionado(entidade.getSexoAnimal(), "Fêmea"));
		prenhaSim.setSelected(getCheckBoxSelecionado(entidade.getPrenhaAnimal(), "Sim"));
		prenhaNao.setSelected(getCheckBoxSelecionado(entidade.getPrenhaAnimal(), "Não"));
		devolvidoSim.setSelected(getCheckBoxSelecionado(entidade.getDevolvidoParaRuaAnimal(), "Sim"));
		devolvidoNao.setSelected(getCheckBoxSelecionado(entidade.getDevolvidoParaRuaAnimal(), "Não"));
		canilSim.setSelected(getCheckBoxSelecionado(entidade.getLevadoCanilAnimal(), "Sim"));
		canilNao.setSelected(getCheckBoxSelecionado(entidade.getLevadoCanilAnimal(), "Não"));
		castradoSim.setSelected(getCheckBoxSelecionado(entidade.getCastradoAnimal(), "Sim"));
		castradoNao.setSelected(getCheckBoxSelecionado(entidade.getCastradoAnimal(), "Não"));
		adocaoSim.setSelected(getCheckBoxSelecionado(entidade.getDispAdocaoAnimal(), "Sim"));
		adocaoNao.setSelected(getCheckBoxSelecionado(entidade.getDispAdocaoAnimal(), "Não"));
		tratamentosAnimal.setText(entidade.getTratamentosAnimal());

	}
	
	public void atualizarAnimal(Animal obj) {
		
		obj.setTamanhoAnimal(Utils.tryParseToDouble(tamanhoAnimal.getText()));
		obj.setPesoAnimal(Utils.tryParseToDouble(pesoAnimal.getText()));
		obj.setCorAnimal(corAnimal.getText());
		obj.setDataResgateAnimal(pegarDataDatePicker(dataResgasteAnimal));
		obj.setVacinasAnimal(vacinaAnimal.getText());
		obj.setSexoAnimal(valorCheckBoxSexo(sexoMacho, sexoFemea));
		obj.setPrenhaAnimal(valorCheckBox(prenhaSim, prenhaNao));
		obj.setDevolvidoParaRuaAnimal(valorCheckBox(devolvidoSim, devolvidoNao));
		obj.setLevadoCanilAnimal(valorCheckBox(canilSim, canilNao));
		obj.setCastradoAnimal(valorCheckBox(castradoSim, castradoNao));
		obj.setDispAdocaoAnimal(valorCheckBox(adocaoSim, adocaoNao));
		obj.setTratamentosAnimal(tratamentosAnimal.getText());

	}
	
	public void verificarDuplicidadeSelecaoCheckBox(CheckBox box1, CheckBox box2) {

		box1.setOnMouseClicked(event -> {
			if (box1.isSelected() && box2.isSelected()) {
				box1.setSelected(true);
				box2.setSelected(false);
			}
		});

		box2.setOnMouseClicked(event -> {
			if (box1.isSelected() && box2.isSelected()) {
				box1.setSelected(false);
				box2.setSelected(true);
			}
		});
	}
	
	public Boolean getCheckBoxSelecionado(String str, String opcao) {
		
		if(str.equalsIgnoreCase(opcao)) {
			return true;
		}
		return false;
	}

	public String valorCheckBoxSexo(CheckBox box1, CheckBox box2) {

		if (box1.isSelected()) {
			return "Macho";
		} else if (box2.isSelected()) {
			return "Fêmea";
		}
		return null;
	}

	public String valorCheckBox(CheckBox box1, CheckBox box2) {

		if (box1.isSelected()) {
			return "Sim";
		} else if (box2.isSelected()) {
			return "Não";
		}
		return null;
	}
	
	public String pegarDataDatePicker(DatePicker dp) {

		LocalDate ld = dp.getValue();
		String date = ld.toString();
		return date;
	}
	
	public LocalDate atualizarDataFormulário(String data) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld = LocalDate.parse(data, dtf);
		return ld;
	}
	
	public void constraintsCheckBoxes(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4) {

		box1.setOnAction(event -> {
			if (box3.isSelected()) {
				box1.setSelected(false);
				box2.setSelected(true);
			}
			verificarDuplicidadeSelecaoCheckBox(box1, box2);
		});
		
		box2.setOnAction(event -> {
			verificarDuplicidadeSelecaoCheckBox(box1, box2);
		});
		box3.setOnAction(event -> {
			verificarDuplicidadeSelecaoCheckBox(box3, box4);
			
			if (box3.isSelected()) {
				box1.setSelected(false);
				box2.setSelected(true);
			}
			if(!(box3.isSelected())){
				box1.setSelected(false);
				box2.setSelected(false);
			}
		});

		box4.setOnAction(event -> {
			verificarDuplicidadeSelecaoCheckBox(box3, box4);
			
		});

	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Constraints.setTextFieldDouble(tamanhoAnimal);
		Constraints.setTextFieldDouble(pesoAnimal);

		verificarDuplicidadeSelecaoCheckBox(devolvidoSim, devolvidoNao);
		verificarDuplicidadeSelecaoCheckBox(castradoSim, castradoNao);
		verificarDuplicidadeSelecaoCheckBox(adocaoSim, adocaoNao);
		constraintsCheckBoxes(prenhaSim, prenhaNao, sexoMacho, sexoFemea);
		constraintsCheckBoxes(canilSim, canilNao, devolvidoSim, devolvidoNao);
		
		
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
