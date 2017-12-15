package restaurante.pagamentos;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.google.gson.GsonBuilder;

public class PedidoCartaoDeCredito extends AbstractPedidoDeVenda<Venda, Venda> {

	PedidoCartaoDeCredito(DadosCielo merchant, AmbienteCielo environment) {
		super(merchant, environment);
	}

	@Override
	public Venda execute(Venda param) throws IOException, CieloRequestException {
		String url = environment.getApiUrl() + "1/sales/";
		HttpPost request = new HttpPost(url);

		request.setEntity(new StringEntity(new GsonBuilder().create().toJson(param)));

		HttpResponse response = sendRequest(request);

		return readResponse(response, Venda.class);
	}

}
