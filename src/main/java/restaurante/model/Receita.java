package restaurante.model;

import java.util.List;

import javax.persistence.CascadeType;
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

import restaurante.controller.ReceitaGrupo;


@Entity
@Table(name = "RECEITAS")
@SequenceGenerator(name = "RECEITAS_SEQUENCE", sequenceName = "RECEITA_SEQ", initialValue = 1, allocationSize = 1)
public class Receita {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEITAS_SEQUENCE")
	@Column
	private Integer id;
	
	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;
	
	@ManyToMany (cascade=CascadeType.ALL)
	@JoinColumn(name = "RECEITAS_GRUPOS_ID", referencedColumnName="ID", foreignKey = @ForeignKey(name = "RECEITAS_GRUPOS_FK"))
	private List<ReceitaGrupo> receitaGrupos;

	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public List<ReceitaGrupo> getReceitaGrupos() {
		return receitaGrupos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//@OneToMany (cascade=CascadeType.ALL, mappedBy="receitas")
	public void setReceitaGrupos(List<ReceitaGrupo> receitaGrupos) {
		this.receitaGrupos = receitaGrupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((receitaGrupos == null) ? 0 : receitaGrupos.hashCode());
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
		Receita other = (Receita) obj;
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
		if (receitaGrupos == null) {
			if (other.receitaGrupos != null)
				return false;
		} else if (!receitaGrupos.equals(other.receitaGrupos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Receita [id=" + id + ", nome=" + nome + ", receitaGrupos=" + receitaGrupos + "]";
	}
	
	public String toJson()
	{
		return "Receita [id=" + id + ", nome=" + nome + "]";
	}
}

