package restaurante.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LANCAMENTOS_ESTOQUES")
@SequenceGenerator(name = "LANCAMENTOS_ESTOQUES_SEQUENCE", sequenceName = "LANCAMENTOS_ESTOQUES_SEQ", initialValue = 1, allocationSize = 1)
public class LancamentoEstoque {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANCAMENTOS_ESTOQUES_SEQUENCE")
	@Column
	private Integer id;
	
	@Column
	private Double quantidade;
	
	@ManyToOne 
	private TipoLancamentoEstoque tipo;
	
	@Column
	private Double valor;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;

	public Integer getId() {
		return id;
	}

	public Double getQuantidade() {
		Double sinal = 1D;
		if (this.getTipo().getCodigo() >= 50)
		{
			sinal = -1D;
		}
		return (quantidade*sinal);
	}

	public TipoLancamentoEstoque getTipo() {
		return tipo;
	}

	public Double getValor() {
		return valor;
	}

	public Date getData() {
		return data;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = Math.abs(quantidade);
	}

	public void setTipo(TipoLancamentoEstoque tipo) {
		this.tipo = tipo;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		LancamentoEstoque other = (LancamentoEstoque) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LancamentoEstoque [id=" + id + ", quantidade=" + quantidade + ", tipo=" + tipo + ", valor=" + valor
				+ ", data=" + data + "]";
	}

}
