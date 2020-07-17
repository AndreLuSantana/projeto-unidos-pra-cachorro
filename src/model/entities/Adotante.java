package model.entities;

import java.io.Serializable;

public class Adotante implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	private Integer idAdotante;
	private String nomeAdotante;
	private String enderecoAdotante;
	private String telefoneAdotante;
	private String emailAdotante;
	private Animal idAnimal;
	
	public Adotante() {
		
	}

	public Adotante(Integer idAdotante, String nomeAdotante, String enderecoAdotante, String telefoneAdotante,
			String emailAdotante, Animal idAnimal) {
		super();
		this.idAdotante = idAdotante;
		this.nomeAdotante = nomeAdotante;
		this.enderecoAdotante = enderecoAdotante;
		this.telefoneAdotante = telefoneAdotante;
		this.emailAdotante = emailAdotante;
		this.idAnimal = idAnimal;
	}

	public Integer getIdAdotante() {
		return idAdotante;
	}

	public void setIdAdotante(Integer idAdotante) {
		this.idAdotante = idAdotante;
	}

	public String getNomeAdotante() {
		return nomeAdotante;
	}

	public void setNomeAdotante(String nomeAdotante) {
		this.nomeAdotante = nomeAdotante;
	}

	public String getEnderecoAdotante() {
		return enderecoAdotante;
	}

	public void setEnderecoAdotante(String enderecoAdotante) {
		this.enderecoAdotante = enderecoAdotante;
	}

	public String getTelefoneAdotante() {
		return telefoneAdotante;
	}

	public void setTelefoneAdotante(String telefoneAdotante) {
		this.telefoneAdotante = telefoneAdotante;
	}

	public String getEmailAdotante() {
		return emailAdotante;
	}

	public void setEmailAdotante(String emailAdotante) {
		this.emailAdotante = emailAdotante;
	}

	public Animal getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Animal idAnimal) {
		this.idAnimal = idAnimal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAdotante == null) ? 0 : emailAdotante.hashCode());
		result = prime * result + ((enderecoAdotante == null) ? 0 : enderecoAdotante.hashCode());
		result = prime * result + ((idAdotante == null) ? 0 : idAdotante.hashCode());
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
		result = prime * result + ((nomeAdotante == null) ? 0 : nomeAdotante.hashCode());
		result = prime * result + ((telefoneAdotante == null) ? 0 : telefoneAdotante.hashCode());
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
		Adotante other = (Adotante) obj;
		if (emailAdotante == null) {
			if (other.emailAdotante != null)
				return false;
		} else if (!emailAdotante.equals(other.emailAdotante))
			return false;
		if (enderecoAdotante == null) {
			if (other.enderecoAdotante != null)
				return false;
		} else if (!enderecoAdotante.equals(other.enderecoAdotante))
			return false;
		if (idAdotante == null) {
			if (other.idAdotante != null)
				return false;
		} else if (!idAdotante.equals(other.idAdotante))
			return false;
		if (idAnimal == null) {
			if (other.idAnimal != null)
				return false;
		} else if (!idAnimal.equals(other.idAnimal))
			return false;
		if (nomeAdotante == null) {
			if (other.nomeAdotante != null)
				return false;
		} else if (!nomeAdotante.equals(other.nomeAdotante))
			return false;
		if (telefoneAdotante == null) {
			if (other.telefoneAdotante != null)
				return false;
		} else if (!telefoneAdotante.equals(other.telefoneAdotante))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adotante [idAdotante=" + idAdotante + ", nomeAdotante=" + nomeAdotante + ", enderecoAdotante="
				+ enderecoAdotante + ", telefoneAdotante=" + telefoneAdotante + ", emailAdotante=" + emailAdotante
				+ ", idAnimal=" + idAnimal + "]";
	}
	
	
}
