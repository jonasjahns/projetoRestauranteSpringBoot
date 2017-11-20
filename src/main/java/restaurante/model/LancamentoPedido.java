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
@Table(name = "LANCAMENTOS_PEDIDOS")
@SequenceGenerator(name = "LANCAMENTOS)PEDIDOS_SEQUENCE", sequenceName = "LANCAMENTOS_PEDIDOS_SEQ", initialValue = 1, allocationSize = 1)
public class LancamentoPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANCAMENTOS)PEDIDOS_SEQUENCE")
	@Column
	private Integer id;

	@Column(name = "data", nullable = true)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name = "hora", nullable = true)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date hora;

	@Column(name = "status")
	private Integer status;

	@ManyToOne
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public Date getHora() {
		return hora;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		LancamentoPedido other = (LancamentoPedido) obj;
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LancamentoPedido [id=" + id + ", data=" + data + ", hora=" + hora + ", status=" + status + "]";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
