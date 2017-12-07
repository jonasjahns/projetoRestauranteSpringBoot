package restaurante.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RECEITAS_GRUPOS")
@SequenceGenerator(name = "RECEITAS_GRUPOS_SEQUENCE", sequenceName = "RECEITAS_GRUPOS_SEQ", initialValue = 1, allocationSize = 1)
public class ReceitaGrupo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEITAS_GRUPOS_SEQUENCE")
	@Column
	private Integer id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "GRUPOS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "RECEITA_GRUPO_GRUPO_FK"))
	private Grupo grupo;

	@Column(name = "EDITAVEL")
	private boolean editavel;

	@Column(name = "NIVEL")
	private int nivel;

	@Column(name = "VARIACAO_MEDIDA")
	private int variacaoMedida;

	@Column(name = "VARIACAO_PRECO")
	private int variacaoPreco;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MEDIDAS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "RECEITA_GRUPO_MEDIDA_FK"))
	private Medida medida;

	@Column(name = "valores", nullable = true)
	@ElementCollection(targetClass = Integer.class)
	private List<Integer> valoresMedida;
	
	@Column(name = "valores", nullable = true)
	@ElementCollection(targetClass = Integer.class)
	private List<Integer> valoresPreco;

	public Integer getId() {
		return id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public boolean isEditavel() {
		return editavel;
	}

	public int getNivel() {
		return nivel;
	}

	public int getVariacaoMedida() {
		return variacaoMedida;
	}

	public int getVariacaoPreco() {
		return variacaoPreco;
	}

	public Medida getMedida() {
		return medida;
	}

	public List<Integer> getValoresMedida() {
		List<Integer> valoresMedida = new ArrayList<Integer>();
		for (int i = 1; i <= this.nivel; i++)
		{
			valoresMedida.add(this.variacaoMedida * i);
		}
		return valoresMedida;
	}

	public List<Integer> getValoresPreco() {
		List<Integer> valoresPreco= new ArrayList<Integer>();
		for (int i = 1; i <= this.nivel; i++)
		{
			valoresPreco.add(this.variacaoPreco * i);
		}
		return valoresPreco;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public void setVariacaoMedida(int variacaoMedida) {
		this.variacaoMedida = variacaoMedida;
	}

	public void setVariacaoPreco(int variacaoPreco) {
		this.variacaoPreco = variacaoPreco;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public void setValoresMedida(List<Integer> valoresMedida) {
		this.valoresMedida = valoresMedida;
	}

	public void setValoresPreco(List<Integer> valoresPreco) {
		this.valoresPreco = valoresPreco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (editavel ? 1231 : 1237);
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medida == null) ? 0 : medida.hashCode());
		result = prime * result + nivel;
		result = prime * result + ((valoresMedida == null) ? 0 : valoresMedida.hashCode());
		result = prime * result + ((valoresPreco == null) ? 0 : valoresPreco.hashCode());
		result = prime * result + variacaoMedida;
		result = prime * result + variacaoPreco;
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
		ReceitaGrupo other = (ReceitaGrupo) obj;
		if (editavel != other.editavel)
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medida == null) {
			if (other.medida != null)
				return false;
		} else if (!medida.equals(other.medida))
			return false;
		if (nivel != other.nivel)
			return false;
		if (valoresMedida == null) {
			if (other.valoresMedida != null)
				return false;
		} else if (!valoresMedida.equals(other.valoresMedida))
			return false;
		if (valoresPreco == null) {
			if (other.valoresPreco != null)
				return false;
		} else if (!valoresPreco.equals(other.valoresPreco))
			return false;
		if (variacaoMedida != other.variacaoMedida)
			return false;
		if (variacaoPreco != other.variacaoPreco)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReceitaGrupo [id=" + id + ", grupo=" + grupo + ", editavel=" + editavel + ", nivel=" + nivel
				+ ", variacaoMedida=" + variacaoMedida + ", variacaoPreco=" + variacaoPreco + ", medida=" + medida
				+ ", valoresMedida=" + valoresMedida + ", valoresPreco=" + valoresPreco + "]";
	}

}
