package restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VALOR_PEDIDO")
@SequenceGenerator(name = "VALOR_PEDIDOS_SEQUENCE", sequenceName = "VALOR_PEDIDOS_SEQ", initialValue = 1, allocationSize = 1)
public class ValorPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VALOR_PEDIDOS_SEQUENCE")
	@Column
	private Integer id;

	@Column(name = "valores", nullable = true)
	private Integer valor;

	@Column(name = "custo", nullable = true)
	private Long custo;

	@ManyToOne
	@JoinColumn(name = "INGREDIENTE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "VALOR_PEDIDO_INGREDIENTE_FK"))
	private Ingrediente ingrediente;

	@ManyToOne
	@JoinColumn(name = "MEDIDA_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "VALOR_PEDIDO_MEDIDA_FK"))
	private Medida medida;

	public Integer getId() {
		return id;
	}

	public Integer getValor() {
		return valor;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ValorPedido other = (ValorPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValorPedido [id=" + id + ", valor=" + valor + ", ingrediente=" + ingrediente + ", medida=" + medida
				+ "]";
	}

	public Long getCusto() {
		return custo;
	}

	public void setCusto(Long custo) {
		this.custo = custo;
	}

}
