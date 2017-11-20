package restaurante.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PEDIDOS")
@SequenceGenerator(name = "PEDIDOS_SEQUENCE", sequenceName = "PEDIDOS_SEQ", initialValue = 1, allocationSize = 1)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_SEQUENCE")
	@Column
	private Integer id;

	@ManyToOne
	private Usuario usuario;

	@OneToOne
	private Receita receita;

	@Column(name = "status")
	private Integer status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "VALORES_PEDIDO_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "VALORE_PEDIDO_PEDIDO_FK"))
	private List<ValorPedido> valores;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "LANCAMENTOS_PEDIDO_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "LANCAMENTOS_PEDIDO_PEDIDO_FK"))
	private List<LancamentoPedido> lancamentosPedido = new ArrayList<LancamentoPedido>();

	@Column(name = "data", nullable = true)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name = "hora", nullable = true)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date hora;

	public String getNomeStatus() {
		String resultado;
		switch (this.status) {
		case 1:
			resultado = "Solicitado";
			break;
		case 2:
			resultado = "Em Preparo";
			break;
		case 3:
			resultado = "Pronto";
			break;
		case 4:
			resultado = "Finalizado";
			break;
		case 5:
			resultado = "Cancelado";
		default:
			resultado = "";
			break;
		}
		return resultado;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Receita getReceita() {
		return receita;
	}

	public Integer getStatus() {
		return status;
	}

	public List<ValorPedido> getValores() {
		return valores;
	}

	public Date getData() {
		return data;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setValores(List<ValorPedido> valores) {
		this.valores = valores;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((receita == null) ? 0 : receita.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valores == null) ? 0 : valores.hashCode());
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
		Pedido other = (Pedido) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (receita == null) {
			if (other.receita != null)
				return false;
		} else if (!receita.equals(other.receita))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valores == null) {
			if (other.valores != null)
				return false;
		} else if (!valores.equals(other.valores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", usuario=" + usuario + ", receita=" + receita + ", status=" + status
				+ ", valores=" + valores + ", data=" + data + ", hora=" + hora + "]";
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public List<LancamentoPedido> getLancamentosPedido() {
		return lancamentosPedido;
	}

	public void setLancamentosPedido(List<LancamentoPedido> lancamentosPedido) {
		this.lancamentosPedido = lancamentosPedido;
	}

	public void setLancamentosPedido(Integer novoStatus, Usuario cozinheiro) {
		LancamentoPedido lancamento = new LancamentoPedido();
		lancamento.setData(new Date());
		lancamento.setHora(new Date());
		lancamento.setUsuario(cozinheiro);
		lancamento.setStatus(novoStatus);
		this.lancamentosPedido.add(lancamento);
		this.setStatus(novoStatus);
	}

	public LancamentoPedido getUltimoStatus() {
		if (this.lancamentosPedido.size() > 0) {
			return this.lancamentosPedido.get(this.lancamentosPedido.size() - 1);
		} else {
			return null;
		}
	}

}
