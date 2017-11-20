package restaurante.model;

import java.io.Serializable;

public enum UsuarioProfileTipo implements Serializable {

	USUARIO("USUARIO"), DBA("DBA"), ADMIN("ADMIN");

	private UsuarioProfileTipo(String usuarioProfileTipo) {
		this.usuarioProfileTipo = usuarioProfileTipo;
	}

	String usuarioProfileTipo;

	public String getUsuarioProfileTipo() {
		return usuarioProfileTipo;
	}

}
