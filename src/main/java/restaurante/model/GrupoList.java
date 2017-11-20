package restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class GrupoList {
	
	public GrupoList() {
		super();
	}

	public GrupoList(List<Grupo> grupoList) {
		super();
		this.grupoList = grupoList;
	}

	private List<Grupo> grupoList = new ArrayList<Grupo>();

	public List<Grupo> getGrupoList() {
		return grupoList;
	}

	public void setGrupoList(List<Grupo> grupoList) {
		this.grupoList = grupoList;
	}
	
	
	
}
