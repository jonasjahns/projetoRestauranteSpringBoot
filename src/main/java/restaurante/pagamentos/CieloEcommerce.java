package restaurante.pagamentos;

import java.io.IOException;

import org.apache.http.client.HttpClient;

public class CieloEcommerce {
	private final DadosCielo merchant;
	private final AmbienteCielo environment;
	private HttpClient httpClient;

	/**
	 * Create an instance of CieloEcommerce choosing the environment where the
	 * requests will be send
	 *
	 * @param merchant
	 *            The merchant credentials
	 * @param environment
	 *            The environment: {@link Environment#PRODUCTION} or
	 *            {@link Environment#SANDBOX}
	 */
	public CieloEcommerce(DadosCielo merchant, AmbienteCielo environment) {
		this.merchant = merchant;
		this.environment = environment;
	}

	/**
	 * Create an instance of CieloEcommerce to work on production environment
	 *
	 * @param merchant
	 *            The merchant credentials
	 */

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Send the Sale to be created and return the Sale with tid and the status
	 * returned by Cielo.
	 *
	 * @param sale
	 *            The preconfigured Sale
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public Venda createSale(Venda sale) throws IOException, CieloRequestException {
		PedidoCartaoDeCredito createSaleRequest = new PedidoCartaoDeCredito(merchant, environment);

		createSaleRequest.setHttpClient(httpClient);

		sale = createSaleRequest.execute(sale);

		return sale;
	}
	public RespostaVenda captureSale(String paymentId, Integer amount, Integer serviceTaxAmount)
			throws IOException, CieloRequestException {
		UpdateSaleRequest updateSaleRequest = new UpdateSaleRequest("capture", merchant, environment);

		updateSaleRequest.setHttpClient(httpClient);
		updateSaleRequest.setAmount(amount);
		updateSaleRequest.setServiceTaxAmount(serviceTaxAmount);

		RespostaVenda sale = updateSaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Capture a Sale on Cielo by paymentId and specifying the amount
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @param amount
	 *            Amount of the authorization to be captured
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public RespostaVenda captureSale(String paymentId, Integer amount) throws IOException, CieloRequestException {
		return captureSale(paymentId, amount, null);
	}

	/**
	 * Capture a Sale on Cielo by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @return The Sale with authorization, tid, etc. returned by Cielo.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 * @see <a href=
	 *      "https://developercielo.github.io/Webservice-3.0/english.html#error-codes">Error
	 *      Codes</a>
	 */
	public RespostaVenda captureSale(String paymentId) throws IOException, CieloRequestException {
		return captureSale(paymentId, null, null);
	}

}
