package restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENTES")
@SequenceGenerator(name = "INGREDIENTES_SEQUENCE", sequenceName = "INGREDIENTES_SEQ", initialValue = 1, allocationSize = 1)
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INGREDIENTES_SEQUENCE")
	@Column
	private Integer id;

	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;

	@Column(name = "carme", nullable = false)
	private Boolean carne;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Boolean getCarne() {
		return carne;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCarne(Boolean carne) {
		this.carne = carne;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carne == null) ? 0 : carne.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Ingrediente other = (Ingrediente) obj;
		if (carne == null) {
			if (other.carne != null)
				return false;
		} else if (!carne.equals(other.carne))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", carne=" + carne + "]";
	}

}
