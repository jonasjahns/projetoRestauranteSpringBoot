package restaurante.pagamentos;

import com.google.gson.annotations.SerializedName;

import restaurante.model.Usuario;

public class Venda {
	public Venda(String idComanda, Usuario usuario) {
		this.idComanda = idComanda;
		this.usuario = usuario;
	}

	@SerializedName("MerchantOrderId")
	private String idComanda;

	@SerializedName("Customer")
	private Usuario usuario;

	@SerializedName("Payment")
	private Payment payment;

	public String getIdComanda() {
		return idComanda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setIdComanda(String idComanda) {
		this.idComanda = idComanda;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
