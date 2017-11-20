package restaurante.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "USUARIOS_PROFILE")
@SequenceGenerator(name = "USUARIOS_PROFILE_SEQUENCE", sequenceName = "USUARIOS_PROFILE_SEQ", initialValue = 2, allocationSize = 1)
public class UsuarioProfile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_PROFILE_SEQUENCE")
	@Column
	private Integer id;

	@Column(name = "TYPE", length = 15, unique = true, nullable = false)
	private String tipo = UsuarioProfileTipo.USUARIO.getUsuarioProfileTipo();

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		UsuarioProfile other = (UsuarioProfile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioProfile [id=" + id + ", tipo=" + tipo + "]";
	}
	
	

}
