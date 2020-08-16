package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idAnimal;
	private Double tamanhoAnimal;
	private Double pesoAnimal;
	private String corAnimal;
	private String dataResgateAnimal;
	private String vacinasAnimal;
	private String sexoAnimal;
	private String prenhaAnimal;
	private String devolvidoParaRuaAnimal;
	private String levadoCanilAnimal;
	private String castradoAnimal;
	private String dispAdocaoAnimal;
	private String tratamentosAnimal;
	
	
	public Animal() {
		
	}
	
	public Animal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}
	
	public Animal(Integer idAnimal, String corAnimal, String sexoAnimal,String dataResgateAnimal) {
		this.idAnimal = idAnimal;
		this.corAnimal = corAnimal;
		this.sexoAnimal = sexoAnimal;
		this.dataResgateAnimal = dataResgateAnimal;
	}

	public Animal(Integer idAnimal, Double tamanhoAnimal, Double pesoAnimal, String corAnimal, String dataResgateAnimal,
			String vacinasAnimal, String sexoAnimal, String prenhaAnimal, String devolvidoParaRuaAnimal,
			String levadoCanilAnimal, String castradoAnimal, String dispAdocaoAnimal, String tratamentosAnimal) {
		
		this.idAnimal = idAnimal;
		this.tamanhoAnimal = tamanhoAnimal;
		this.pesoAnimal = pesoAnimal;
		this.corAnimal = corAnimal;
		this.dataResgateAnimal = dataResgateAnimal;
		this.vacinasAnimal = vacinasAnimal;
		this.sexoAnimal = sexoAnimal;
		this.prenhaAnimal = prenhaAnimal;
		this.devolvidoParaRuaAnimal = devolvidoParaRuaAnimal;
		this.levadoCanilAnimal = levadoCanilAnimal;
		this.castradoAnimal = castradoAnimal;
		this.dispAdocaoAnimal = dispAdocaoAnimal;
		this.tratamentosAnimal = tratamentosAnimal;
	}

	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

	public Double getTamanhoAnimal() {
		return tamanhoAnimal;
	}

	public void setTamanhoAnimal(Double tamanhoAnimal) {
		this.tamanhoAnimal = tamanhoAnimal;
	}

	public Double getPesoAnimal() {
		return pesoAnimal;
	}

	public void setPesoAnimal(Double pesoAnimal) {
		this.pesoAnimal = pesoAnimal;
	}

	public String getCorAnimal() {
		return corAnimal;
	}

	public void setCorAnimal(String corAnimal) {
		this.corAnimal = corAnimal;
	}

	public String getDataResgateAnimal() {
		return dataResgateAnimal;
	}

	public void setDataResgateAnimal(String dataResgateAnimal) {
		this.dataResgateAnimal = dataResgateAnimal;
	}

	public String getVacinasAnimal() {
		return vacinasAnimal;
	}

	public void setVacinasAnimal(String vacinasAnimal) {
		this.vacinasAnimal = vacinasAnimal;
	}

	public String getSexoAnimal() {
		return sexoAnimal;
	}

	public void setSexoAnimal(String sexoAnimal) {
		this.sexoAnimal = sexoAnimal;
	}

	public String getPrenhaAnimal() {
		return prenhaAnimal;
	}

	public void setPrenhaAnimal(String prenhaAnimal) {
		this.prenhaAnimal = prenhaAnimal;
	}

	public String getDevolvidoParaRuaAnimal() {
		return devolvidoParaRuaAnimal;
	}

	public void setDevolvidoParaRuaAnimal(String devolvidoParaRuaAnimal) {
		this.devolvidoParaRuaAnimal = devolvidoParaRuaAnimal;
	}

	public String getLevadoCanilAnimal() {
		return levadoCanilAnimal;
	}

	public void setLevadoCanilAnimal(String levadoCanilAnimal) {
		this.levadoCanilAnimal = levadoCanilAnimal;
	}

	public String getCastradoAnimal() {
		return castradoAnimal;
	}

	public void setCastradoAnimal(String castradoAnimal) {
		this.castradoAnimal = castradoAnimal;
	}

	public String getDispAdocaoAnimal() {
		return dispAdocaoAnimal;
	}

	public void setDispAdocaoAnimal(String dispAdocaoAnimal) {
		this.dispAdocaoAnimal = dispAdocaoAnimal;
	}

	public String getTratamentosAnimal() {
		return tratamentosAnimal;
	}

	public void setTratamentosAnimal(String tratamentosAnimal) {
		this.tratamentosAnimal = tratamentosAnimal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((castradoAnimal == null) ? 0 : castradoAnimal.hashCode());
		result = prime * result + ((corAnimal == null) ? 0 : corAnimal.hashCode());
		result = prime * result + ((dataResgateAnimal == null) ? 0 : dataResgateAnimal.hashCode());
		result = prime * result + ((devolvidoParaRuaAnimal == null) ? 0 : devolvidoParaRuaAnimal.hashCode());
		result = prime * result + ((dispAdocaoAnimal == null) ? 0 : dispAdocaoAnimal.hashCode());
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
		result = prime * result + ((levadoCanilAnimal == null) ? 0 : levadoCanilAnimal.hashCode());
		result = prime * result + ((pesoAnimal == null) ? 0 : pesoAnimal.hashCode());
		result = prime * result + ((prenhaAnimal == null) ? 0 : prenhaAnimal.hashCode());
		result = prime * result + ((sexoAnimal == null) ? 0 : sexoAnimal.hashCode());
		result = prime * result + ((tamanhoAnimal == null) ? 0 : tamanhoAnimal.hashCode());
		result = prime * result + ((tratamentosAnimal == null) ? 0 : tratamentosAnimal.hashCode());
		result = prime * result + ((vacinasAnimal == null) ? 0 : vacinasAnimal.hashCode());
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
		if (castradoAnimal == null) {
			if (other.castradoAnimal != null)
				return false;
		} else if (!castradoAnimal.equals(other.castradoAnimal))
			return false;
		if (corAnimal == null) {
			if (other.corAnimal != null)
				return false;
		} else if (!corAnimal.equals(other.corAnimal))
			return false;
		if (dataResgateAnimal == null) {
			if (other.dataResgateAnimal != null)
				return false;
		} else if (!dataResgateAnimal.equals(other.dataResgateAnimal))
			return false;
		if (devolvidoParaRuaAnimal == null) {
			if (other.devolvidoParaRuaAnimal != null)
				return false;
		} else if (!devolvidoParaRuaAnimal.equals(other.devolvidoParaRuaAnimal))
			return false;
		if (dispAdocaoAnimal == null) {
			if (other.dispAdocaoAnimal != null)
				return false;
		} else if (!dispAdocaoAnimal.equals(other.dispAdocaoAnimal))
			return false;
		if (idAnimal == null) {
			if (other.idAnimal != null)
				return false;
		} else if (!idAnimal.equals(other.idAnimal))
			return false;
		if (levadoCanilAnimal == null) {
			if (other.levadoCanilAnimal != null)
				return false;
		} else if (!levadoCanilAnimal.equals(other.levadoCanilAnimal))
			return false;
		if (pesoAnimal == null) {
			if (other.pesoAnimal != null)
				return false;
		} else if (!pesoAnimal.equals(other.pesoAnimal))
			return false;
		if (prenhaAnimal == null) {
			if (other.prenhaAnimal != null)
				return false;
		} else if (!prenhaAnimal.equals(other.prenhaAnimal))
			return false;
		if (sexoAnimal == null) {
			if (other.sexoAnimal != null)
				return false;
		} else if (!sexoAnimal.equals(other.sexoAnimal))
			return false;
		if (tamanhoAnimal == null) {
			if (other.tamanhoAnimal != null)
				return false;
		} else if (!tamanhoAnimal.equals(other.tamanhoAnimal))
			return false;
		if (tratamentosAnimal == null) {
			if (other.tratamentosAnimal != null)
				return false;
		} else if (!tratamentosAnimal.equals(other.tratamentosAnimal))
			return false;
		if (vacinasAnimal == null) {
			if (other.vacinasAnimal != null)
				return false;
		} else if (!vacinasAnimal.equals(other.vacinasAnimal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [idAnimal=" + idAnimal + ", tamanhoAnimal=" + tamanhoAnimal + ", pesoAnimal=" + pesoAnimal
				+ ", corAnimal=" + corAnimal + ", dataResgateAnimal=" + dataResgateAnimal + ", vacinasAnimal="
				+ vacinasAnimal + ", sexoAnimal=" + sexoAnimal + ", prenhaAnimal=" + prenhaAnimal
				+ ", devolvidoParaRuaAnimal=" + devolvidoParaRuaAnimal + ", levadoCanilAnimal=" + levadoCanilAnimal
				+ ", castradoAnimal=" + castradoAnimal + ", dispAdocaoAnimal=" + dispAdocaoAnimal
				+ ", tratamentosAnimal=" + tratamentosAnimal + "]";
	}
	
	
	
}	