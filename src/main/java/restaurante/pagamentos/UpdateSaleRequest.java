package restaurante.pagamentos;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;

public class UpdateSaleRequest extends AbstractPedidoDeVenda<String, RespostaVenda> {
	private final String type;
	private Integer amount;
	private Integer serviceTaxAmount;

	public UpdateSaleRequest(String type, DadosCielo merchant, AmbienteCielo environment) {
		super(merchant, environment);
		this.type = type;
	}

	@Override
	public RespostaVenda execute(String paymentId) throws IOException, CieloRequestException {
		RespostaVenda sale = null;

		try {
			URIBuilder builder = new URIBuilder(environment.getApiUrl() + "1/sales/" + paymentId + "/" + type);

			if (amount != null) {
				builder.addParameter("amount", amount.toString());
			}

			if (serviceTaxAmount != null) {
				builder.addParameter("serviceTaxAmount", serviceTaxAmount.toString());
			}

			HttpPut request;

			request = new HttpPut(builder.build().toString());

			HttpResponse response = sendRequest(request);

			sale = readResponse(response, RespostaVenda.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return sale;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setServiceTaxAmount(Integer serviceTaxAmount) {
		this.serviceTaxAmount = serviceTaxAmount;
	}
}
