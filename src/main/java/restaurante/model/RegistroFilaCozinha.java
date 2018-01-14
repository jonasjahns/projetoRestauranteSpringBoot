package restaurante.model;

import com.google.gson.annotations.SerializedName;

public class RegistroFilaCozinha {

	public String prato;

	public String cliente;

	public String status;

	public String horaPedido;

	@SerializedName("atualizado")
	public String ultimaAtualizacao;

	public final String botao = "botao";

	public String getPrato() {
		return prato;
	}

	public String getCliente() {
		return cliente;
	}

	public String getStatus() {
		return status;
	}

	public String getHoraPedido() {
		return horaPedido;
	}

	public String getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public String getBotao() {
		return botao;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}

	public void setUltimaAtualizacao(String ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
