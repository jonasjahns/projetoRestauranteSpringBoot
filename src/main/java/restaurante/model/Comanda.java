package restaurante.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "COMANDA")
@SequenceGenerator(name = "COMANDA_SEQUENCE", sequenceName = "COMANDA_SEQ", initialValue = 1, allocationSize = 1)
public class Comanda {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMANDA_SEQUENCE")
	@Column
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Pedido> pedidos;

	@ManyToOne
	private Usuario usuario;

	@Column(name = "data_inicial", nullable = true)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicial;

	@Column(name = "hora_inicial", nullable = true)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horaInicial;

	@Column(name = "data_final", nullable = true)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataFinal;

	@Column(name = "hora_final", nullable = true)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date horaFinal;

	@Column(name = "canal", nullable = true)
	private Integer canal; // 1 = Caixa, 2 = Token, 3 = Internet

	@Column(name = "id_pagamento", nullable = true)
	private Integer idPagamento;

	public Integer getId() {
		return id;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public Date getHoraInicial() {
		return horaInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public Integer getCanal() {
		return canal;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public void setCanal(Integer canal) {
		this.canal = canal;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horaFinal == null) ? 0 : horaFinal.hashCode());
		result = prime * result + ((horaInicial == null) ? 0 : horaInicial.hashCode());
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Comanda other = (Comanda) obj;
		if (horaFinal == null) {
			if (other.horaFinal != null)
				return false;
		} else if (!horaFinal.equals(other.horaFinal))
			return false;
		if (horaInicial == null) {
			if (other.horaInicial != null)
				return false;
		} else if (!horaInicial.equals(other.horaInicial))
			return false;
		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comanda [id=" + id + ", pedidos=" + pedidos + ", usuario=" + usuario + ", dataInicial=" + dataInicial
				+ ", horaInicial=" + horaInicial + ", dataFinal=" + dataFinal + ", horaFinal=" + horaFinal + ", canal="
				+ canal + ", idPagamento=" + idPagamento + "]";
	}

	public String getHoraInicialFormatada() {

		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		return hora.format(this.horaInicial);
	}

	public String getHoraFinalFormatada() {
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		return hora.format(this.horaFinal);
	}

	public String getDataInicialFormatada() {
		DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		return data.format(this.dataInicial);
	}

	public String getDataFinalFormatada() {
		DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		return data.format(this.dataFinal);
	}
	
	public Long getTotal()
	{
		Long total = new Long(0);
		for (Pedido pedido : this.pedidos)
		{
			total += pedido.getCusto();
		}
		return total;
	}

}
