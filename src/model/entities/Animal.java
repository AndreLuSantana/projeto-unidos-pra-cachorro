package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idAnimal;
	private Double tamanho;
	private Double peso;
	private String cor;
	private Date dataResgate;
	private String vacinas;
	private String sexo;
	private String prenha;
	private String devolvidoParaRua;
	private String levadoCanil;
	private String castrado;
	private String dispAdocao;
	private String tratamentos;
	
	public Animal() {
		
	}
	
	public Animal(Integer idAnimal, Double tamanho, Double peso, String cor, Date dataResgate, String vacinas,
			String sexo, String prenha, String devolvidoParaRua, String levadoCanil, String castrado, String dispAdocao,
			String tratamentos) {
		
		this.idAnimal = idAnimal;
		this.tamanho = tamanho;
		this.peso = peso;
		this.cor = cor;
		this.dataResgate = dataResgate;
		this.vacinas = vacinas;
		this.sexo = sexo;
		this.prenha = prenha;
		this.devolvidoParaRua = devolvidoParaRua;
		this.levadoCanil = levadoCanil;
		this.castrado = castrado;
		this.dispAdocao = dispAdocao;
		this.tratamentos = tratamentos;
	}

	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Date getDataResgate() {
		return dataResgate;
	}

	public void setDataResgate(Date dataResgate) {
		this.dataResgate = dataResgate;
	}

	public String getVacinas() {
		return vacinas;
	}

	public void setVacinas(String vacinas) {
		this.vacinas = vacinas;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPrenha() {
		return prenha;
	}

	public void setPrenha(String prenha) {
		this.prenha = prenha;
	}

	public String getDevolvidoParaRua() {
		return devolvidoParaRua;
	}

	public void setDevolvidoParaRua(String devolvidoParaRua) {
		this.devolvidoParaRua = devolvidoParaRua;
	}

	public String getLevadoCanil() {
		return levadoCanil;
	}

	public void setLevadoCanil(String levadoCanil) {
		this.levadoCanil = levadoCanil;
	}

	public String getCastrado() {
		return castrado;
	}

	public void setCastrado(String castrado) {
		this.castrado = castrado;
	}

	public String getDispAdocao() {
		return dispAdocao;
	}

	public void setDispAdocao(String dispAdocao) {
		this.dispAdocao = dispAdocao;
	}

	public String getTratamentos() {
		return tratamentos;
	}

	public void setTratamentos(String tratamentos) {
		this.tratamentos = tratamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((castrado == null) ? 0 : castrado.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((dataResgate == null) ? 0 : dataResgate.hashCode());
		result = prime * result + ((devolvidoParaRua == null) ? 0 : devolvidoParaRua.hashCode());
		result = prime * result + ((dispAdocao == null) ? 0 : dispAdocao.hashCode());
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
		result = prime * result + ((levadoCanil == null) ? 0 : levadoCanil.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((prenha == null) ? 0 : prenha.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tratamentos == null) ? 0 : tratamentos.hashCode());
		result = prime * result + ((vacinas == null) ? 0 : vacinas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (castrado == null) {
			if (other.castrado != null)
				return false;
		} else if (!castrado.equals(other.castrado))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (dataResgate == null) {
			if (other.dataResgate != null)
				return false;
		} else if (!dataResgate.equals(other.dataResgate))
			return false;
		if (devolvidoParaRua == null) {
			if (other.devolvidoParaRua != null)
				return false;
		} else if (!devolvidoParaRua.equals(other.devolvidoParaRua))
			return false;
		if (dispAdocao == null) {
			if (other.dispAdocao != null)
				return false;
		} else if (!dispAdocao.equals(other.dispAdocao))
			return false;
		if (idAnimal == null) {
			if (other.idAnimal != null)
				return false;
		} else if (!idAnimal.equals(other.idAnimal))
			return false;
		if (levadoCanil == null) {
			if (other.levadoCanil != null)
				return false;
		} else if (!levadoCanil.equals(other.levadoCanil))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (prenha == null) {
			if (other.prenha != null)
				return false;
		} else if (!prenha.equals(other.prenha))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tratamentos == null) {
			if (other.tratamentos != null)
				return false;
		} else if (!tratamentos.equals(other.tratamentos))
			return false;
		if (vacinas == null) {
			if (other.vacinas != null)
				return false;
		} else if (!vacinas.equals(other.vacinas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [idAnimal=" + idAnimal + ", tamanho=" + tamanho + ", peso=" + peso + ", cor=" + cor
				+ ", dataResgate=" + dataResgate + ", vacinas=" + vacinas + ", sexo=" + sexo + ", prenha=" + prenha
				+ ", devolvidoParaRua=" + devolvidoParaRua + ", levadoCanil=" + levadoCanil + ", castrado=" + castrado
				+ ", dispAdocao=" + dispAdocao + ", tratamentos=" + tratamentos + "]";
	}
	
	
	
}
