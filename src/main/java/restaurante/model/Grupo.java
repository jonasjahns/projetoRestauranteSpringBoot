package restaurante.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "GRUPOS")
@SequenceGenerator(name = "GRUPOS_SEQUENCE", sequenceName = "GRUPOS_SEQ", initialValue = 1, allocationSize = 1)
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOS_SEQUENCE")
	@Column
	private Integer id;

	@NotEmpty
	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;

	@ManyToMany
	@JoinColumn(name = "INGREDIENTE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "GRUPO_INGREDIENTE_FK"))
	private List<Ingrediente> ingredientes;

	@ManyToMany
	@JoinColumn(name = "MEDIDA_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "INGREDIENTE_MEDIDA_FK"))
	private List<Medida> medidas;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public List<Medida> getMedidas() {
		return medidas;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public void setMedidas(List<Medida> medidas) {
		this.medidas = medidas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingredientes == null) ? 0 : ingredientes.hashCode());
		result = prime * result + ((medidas == null) ? 0 : medidas.hashCode());
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingredientes == null) {
			if (other.ingredientes != null)
				return false;
		} else if (!ingredientes.equals(other.ingredientes))
			return false;
		if (medidas == null) {
			if (other.medidas != null)
				return false;
		} else if (!medidas.equals(other.medidas))
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
		return "Grupo [id=" + id + ", nome=" + nome + ", ingredientes=" + ingredientes + ", medidas=" + medidas + "]";
	}

}
