package restaurante.model;

import java.util.ArrayList;

public class PedidoAndroid {

	private ArrayList<ValorPedido> valoresPedido;
	private String idUsuario;
	private String idReceita;
	private String status;

	public ArrayList<ValorPedido> getValoresPedido() {
		return valoresPedido;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public String getIdReceita() {
		return idReceita;
	}

	public String getStatus() {
		return status;
	}

	public void setValoresPedido(ArrayList<ValorPedido> valoresPedido) {
		this.valoresPedido = valoresPedido;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdReceita(String idReceita) {
		this.idReceita = idReceita;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PedidoAndroid [valoresPedido=" + valoresPedido + ", idUsuario=" + idUsuario + ", idReceita=" + idReceita
				+ ", status=" + status + "]";
	}

}
