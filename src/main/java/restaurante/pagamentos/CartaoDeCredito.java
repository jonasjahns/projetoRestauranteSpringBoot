package restaurante.pagamentos;

import com.google.gson.annotations.SerializedName;

public class CartaoDeCredito {

	public CartaoDeCredito(String codigoSeguranca, String marca) {
		super();
		this.codigoSeguranca = codigoSeguranca;
		this.marca = marca;
	}

	public CartaoDeCredito() {
	}

	@SerializedName("CardNumber")
	private String numeroCartao;

	@SerializedName("Holder")
	private String portador;

	@SerializedName("ExpirationDate")
	private String dataVencimento;

	@SerializedName("SecurityCode")
	private String codigoSeguranca;

	@SerializedName("SaveCard")
	private boolean salvarCartao = false;

	@SerializedName("Brand")
	private String marca;

	@SerializedName("CardToken")
	private String tokenCartao;

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

	public boolean isSalvarCartao() {
		return salvarCartao;
	}

	public String getMarca() {
		return marca;
	}

	public String getTokenCartao() {
		return tokenCartao;
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

	public void setSalvarCartao(boolean salvarCartao) {
		this.salvarCartao = salvarCartao;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setTokenCartao(String tokenCartao) {
		this.tokenCartao = tokenCartao;
	}

}
