package restaurante.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.SerializedName;

@Entity
@Table(name = "USUARIOS")
@SequenceGenerator(name = "USUARIOS_SEQUENCE", sequenceName = "USUARIOS_SEQ", initialValue = 2, allocationSize = 1)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_SEQUENCE")
	@Column
	private Integer id;

	@NotEmpty
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@NotEmpty
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	@SerializedName("Name")
	private String firstName;

	@NotEmpty
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE", joinColumns = {
			@JoinColumn(name = "USUARIOS_ID") }, inverseJoinColumns = { @JoinColumn(name = "USUARIOS_PROFILE_ID") })
	private Set<UsuarioProfile> usuarioProfile = new HashSet<UsuarioProfile>();

	public Integer getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<UsuarioProfile> getUsuarioProfile() {
		return usuarioProfile;
	}

	public void setUsuarioProfile(Set<UsuarioProfile> usuarioProfile) {
		this.usuarioProfile = usuarioProfile;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cpf=" + cpf + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", usuarioProfile=" + usuarioProfile + "]";
	}

	public String getNomeCompleto() {
		return this.firstName + " " + this.lastName;
	}

}
