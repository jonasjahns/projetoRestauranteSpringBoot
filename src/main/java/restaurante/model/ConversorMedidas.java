package restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONVERSOR_MEDIDAS")
@SequenceGenerator(name = "CONVERSOR_MEDIDAS_SEQUENCE", sequenceName = "CONVERSOR_MEDIDAS_SEQ", initialValue = 1, allocationSize = 1)
public class ConversorMedidas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONVERSOR_MEDIDAS_SEQUENCE")
	@Column
	private Integer id;
	
	@ManyToOne
	private Medida de;
	
	@ManyToOne
	private Medida para;
	
	@ManyToOne (optional = true, fetch= FetchType.LAZY)
	private Ingrediente ingrediente;
	
	@Column
	private Double taxa;

	@Column
	private Boolean padrao;
	
	public Double converter(Double valor)
	{
		return valor*this.taxa;
	}


	public Integer getId() {
		return id;
	}


	public Medida getDe() {
		return de;
	}


	public Medida getPara() {
		return para;
	}


	public Ingrediente getIngrediente() {
		return ingrediente;
	}


	public Double getTaxa() {
		return taxa;
	}


	public Boolean getPadrao() {
		return padrao;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setDe(Medida de) {
		this.de = de;
	}


	public void setPara(Medida para) {
		this.para = para;
	}


	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}


	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}


	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((de == null) ? 0 : de.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingrediente == null) ? 0 : ingrediente.hashCode());
		result = prime * result + ((padrao == null) ? 0 : padrao.hashCode());
		result = prime * result + ((para == null) ? 0 : para.hashCode());
		result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
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
		ConversorMedidas other = (ConversorMedidas) obj;
		if (de == null) {
			if (other.de != null)
				return false;
		} else if (!de.equals(other.de))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingrediente == null) {
			if (other.ingrediente != null)
				return false;
		} else if (!ingrediente.equals(other.ingrediente))
			return false;
		if (padrao == null) {
			if (other.padrao != null)
				return false;
		} else if (!padrao.equals(other.padrao))
			return false;
		if (para == null) {
			if (other.para != null)
				return false;
		} else if (!para.equals(other.para))
			return false;
		if (taxa == null) {
			if (other.taxa != null)
				return false;
		} else if (!taxa.equals(other.taxa))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ConversorMedidas [id=" + id + ", de=" + de + ", para=" + para + ", ingrediente=" + ingrediente
				+ ", taxa=" + taxa + ", padrao=" + padrao + "]";
	}

}
