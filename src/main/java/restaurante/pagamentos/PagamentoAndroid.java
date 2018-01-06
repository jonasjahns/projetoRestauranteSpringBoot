package restaurante.pagamentos;

import java.util.ArrayList;

public class PagamentoAndroid {
	private String idUsuario;

	private ArrayList<String> idPedido;

	private String numeroCartao;

	private String portador;

	private String dataVencimento;

	private String codigoSeguranca;

	public String getIdUsuario() {
		return idUsuario;
	}

	public ArrayList<String> getIdPedido() {
		return idPedido;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public String getPortador() {
		return portador;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdPedido(ArrayList<String> idPedido) {
		this.idPedido = idPedido;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public void setPortador(String portador) {
		this.portador = portador;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoSeguranca == null) ? 0 : codigoSeguranca.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((numeroCartao == null) ? 0 : numeroCartao.hashCode());
		result = prime * result + ((portador == null) ? 0 : portador.hashCode());
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
		PagamentoAndroid other = (PagamentoAndroid) obj;
		if (codigoSeguranca == null) {
			if (other.codigoSeguranca != null)
				return false;
		} else if (!codigoSeguranca.equals(other.codigoSeguranca))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (numeroCartao == null) {
			if (other.numeroCartao != null)
				return false;
		} else if (!numeroCartao.equals(other.numeroCartao))
			return false;
		if (portador == null) {
			if (other.portador != null)
				return false;
		} else if (!portador.equals(other.portador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PagamentoAndroid [idUsuario=" + idUsuario + ", idPedido=" + idPedido + ", numeroCartao=" + numeroCartao
				+ ", portador=" + portador + ", dataVencimento=" + dataVencimento + ", codigoSeguranca="
				+ codigoSeguranca + "]";
	}

}
