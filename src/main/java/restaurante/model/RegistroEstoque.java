package restaurante.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "REGISTROS_ESTOQUES")
@SequenceGenerator(name = "REGISTROS_ESTOQUES_SEQUENCE", sequenceName = "REGISTROS_ESTOQUES_SEQ", initialValue = 1, allocationSize = 1)
public class RegistroEstoque {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTROS_ESTOQUES_SEQUENCE")
	@Column
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "MEDIDAS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "REGISTRO_MEDIDA_FK"))
	private Medida medida;

	@ManyToOne
	@JoinColumn(name = "INGREDIENTE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "REGISTRO_INGREDIENTE_FK"))
	private Ingrediente ingrediente;

	@Column
	private Double quantidadeComprada;

	@OneToMany
	@JoinColumn(name = "LANCAMENTOS_ESTOQUES_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "REGISTRO_LANCAMENTO_FK"))
	private List<LancamentoEstoque> lancamentos;

	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date validade;

	@Column
	private Double valor;

	public Integer getId() {
		return id;
	}

	public Medida getMedida() {
		return medida;
	}

	public Double getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public List<LancamentoEstoque> getLancamentos() {
		return lancamentos;
	}

	public Date getValidade() {
		return validade;
	}

	public Double getValor() {
		return valor;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public void setQuantidadeComprada(Double quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public void setLancamentos(List<LancamentoEstoque> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ingrediente == null) ? 0 : ingrediente.hashCode());
		result = prime * result + ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result + ((medida == null) ? 0 : medida.hashCode());
		result = prime * result + ((quantidadeComprada == null) ? 0 : quantidadeComprada.hashCode());
		result = prime * result + ((validade == null) ? 0 : validade.hashCode());
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
		RegistroEstoque other = (RegistroEstoque) obj;
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
		if (lancamentos == null) {
			if (other.lancamentos != null)
				return false;
		} else if (!lancamentos.equals(other.lancamentos))
			return false;
		if (medida == null) {
			if (other.medida != null)
				return false;
		} else if (!medida.equals(other.medida))
			return false;
		if (quantidadeComprada == null) {
			if (other.quantidadeComprada != null)
				return false;
		} else if (!quantidadeComprada.equals(other.quantidadeComprada))
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
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
		return "RegistroEstoque [id=" + id + ", medida=" + medida + ", quantidadeComprada=" + quantidadeComprada
				+ ", lancamentos=" + lancamentos + ", validade=" + validade + ", valor=" + valor + "]";
	}

	public Boolean isDisponivel(Double quantidade) {
		Double soma = quantidade + this.getConsumidos();
		if (soma <= this.quantidadeComprada) {
			return true;
		}
		return false;
	}

	public Double getDisponiveis() {
		Double consumidos = this.getConsumidos();
		return (this.quantidadeComprada - consumidos);
	}

	public Double getConsumidos() {
		Double soma = 0D;
		for (LancamentoEstoque lancamento : this.lancamentos) {
			soma = soma + lancamento.getQuantidade();
		}
		return soma;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getDataValidade() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.getValidade());
	}

	public void adicionarLancamento(LancamentoEstoque lancamentoEstoque) {
		this.lancamentos.add(lancamentoEstoque);
	}
}
