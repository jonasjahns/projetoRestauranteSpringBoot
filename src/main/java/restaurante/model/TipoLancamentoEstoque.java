package restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_LANCAMENTO_ESTOQUE")
@SequenceGenerator(name = "TIPO_LANCAMENTO_ESTOQUE_SEQUENCE", sequenceName = "TIPO_LANCAMENTO_ESTOQUE_SEQ", initialValue = 10, allocationSize = 1)
public class TipoLancamentoEstoque {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_LANCAMENTO_ESTOQUE_SEQUENCE")
	@Column
	private Integer id;

	@Column(name = "codigos", nullable = true)
	private Integer codigo;
	
	@Column (name = "nomes", nullable = false)
	private String nome;

	public Integer getId() {
		return id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		TipoLancamentoEstoque other = (TipoLancamentoEstoque) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
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
		return "TipoLancamentoEstoque [id=" + id + ", codigo=" + codigo + ", nome=" + nome + "]";
	}
	
	
}
