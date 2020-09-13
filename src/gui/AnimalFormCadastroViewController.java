package gui;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.entities.Animal;
import model.services.AnimalService;
import model.services.LoadViewService;

public class AnimalFormCadastroViewController implements Initializable {

	private Animal entidade;
	private AnimalService service = new AnimalService();

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

	public Animal instanciarAnimal(Animal obj) {

		obj.setTamanhoAnimal(Utils.tryParseToDouble(tamanhoAnimal.getText()));
		obj.setPesoAnimal(Utils.tryParseToDouble(pesoAnimal.getText()));
		obj.setCorAnimal(corAnimal.getText());
		obj.setDataResgateAnimal(pegarDataDatePicker(dataResgasteAnimal));
		obj.setVacinasAnimal(vacinasAnimal.getText());
		obj.setSexoAnimal(valorCheckBoxSexo(sexoMacho, sexoFemea));
		obj.setPrenhaAnimal(valorCheckBox(prenhaSim, prenhaNao));
		obj.setDevolvidoParaRuaAnimal(valorCheckBox(devolvidoSim, devolvidoNao));
		obj.setLevadoCanilAnimal(valorCheckBox(canilSim, canilNao));
		obj.setCastradoAnimal(valorCheckBox(castradoSim, castradoNao));
		obj.setDispAdocaoAnimal(valorCheckBox(adocaoSim, adocaoNao));
		obj.setTratamentosAnimal(tratamentoAnimal.getText());

		return obj;
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

	public void apagarCampos() {

		LoadViewService lvs = new LoadViewService();
		lvs.loadView("/gui/AnimalFormCadastroView.fxml", x -> {
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
		
		 

		btnCancelar.setOnAction(event -> {
			Utils.currentStage(event);

			if (event != null) {
				Optional<ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR CANCELAMENTO",
						"Você tem certeza que deseja cancelar?");
				if (resultado.get() == ButtonType.OK) {

					Alerts.showAlert("CADASTRO CANCELADO", null, "Cancelamento efetuado com sucesso",
							AlertType.CONFIRMATION);
					apagarCampos();
				}
			}
		});

		btnSalvar.setOnAction(event -> {
			Utils.currentStage(event);

			if (event != null) {
				Optional<ButtonType> resultado = Alerts.mostrarConfirmacao("CONFIRMAR CADASTRAMENTO",
						"Você tem certeza que deseja cadastrar este animal?");

				if (resultado.get() == ButtonType.OK) {

					Alerts.showAlert("CADASTRO EFETUADO", null, "Cadastro realizado com sucesso",
							AlertType.CONFIRMATION);

					Animal animal = new Animal();
					instanciarAnimal(animal);
					service.insertOrUpdate(animal);
					apagarCampos();

				} else {

					Alerts.showAlert("CADASTRO CANCELADO", null, "Cadastramento Cancelado", AlertType.CONFIRMATION);
					apagarCampos();
				}
			}
		});

	}

}
