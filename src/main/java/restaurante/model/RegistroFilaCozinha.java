package restaurante.model;

public class RegistroFilaCozinha {

	public String nome;

	public String usuario;

	public String status;

	public String data;

	public String dataStatus;

	public final String botao = "botao";

	public String getNome() {
		return nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getStatus() {
		return status;
	}

	public String getData() {
		return data;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public String getBotao() {
		return botao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	@Override
	public String toString() {
		return "RegistroFilaCozinha [nome=" + nome + ", usuario=" + usuario + ", status=" + status + ", data=" + data
				+ ", dataStatus=" + dataStatus + ", botao=" + botao + "]";
	}

}
