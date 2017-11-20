package restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MEDIDAS")
@SequenceGenerator(name = "MEDIDAS_SEQUENCE", sequenceName = "MEDIDAS_SEQ", initialValue = 1, allocationSize = 1)
public class Medida {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDIDAS_SEQUENCE")
	@Column
	private Integer id;

	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;

	@Column(name = "ABREVIATURA", length = 100, nullable = false)
	private String abreviatura;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreviatura == null) ? 0 : abreviatura.hashCode());
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
		Medida other = (Medida) obj;
		if (abreviatura == null) {
			if (other.abreviatura != null)
				return false;
		} else if (!abreviatura.equals(other.abreviatura))
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
		return "Medida [id=" + id + ", nome=" + nome + ", abreviatura=" + abreviatura + "]";
	}

}
