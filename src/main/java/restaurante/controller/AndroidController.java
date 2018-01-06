package restaurante.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurante.model.Comanda;
import restaurante.model.Pedido;
import restaurante.model.PedidoAndroid;
import restaurante.model.Receita;
import restaurante.model.Usuario;
import restaurante.pagamentos.AmbienteCielo;
import restaurante.pagamentos.CieloEcommerce;
import restaurante.pagamentos.CieloError;
import restaurante.pagamentos.CieloRequestException;
import restaurante.pagamentos.DadosCielo;
import restaurante.pagamentos.PagamentoAndroid;
import restaurante.pagamentos.Payment;
import restaurante.pagamentos.RespostaVenda;
import restaurante.pagamentos.ValidaCartao;
import restaurante.pagamentos.Venda;
import restaurante.service.ComandaService;
import restaurante.service.MedidaService;
import restaurante.service.PedidoService;
import restaurante.service.ReceitaService;
import restaurante.service.UsuarioService;

@RestController
@RequestMapping("/android/")
public class AndroidController {

	@Autowired
	ReceitaService receitaService;

	@Autowired
	MedidaService medidaService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ComandaService comandaService;

	@RequestMapping(value = { "/disponiveis/" }, method = RequestMethod.GET, produces = "application/json")
	public List<Receita> receitasDisponiveis() {
		return receitaService.receitasDisponiveis();
	}

	@RequestMapping(value = { "/receita/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public Receita receitaPorId(@PathVariable Integer id) {
		return receitaService.getById(id);
	}

	@RequestMapping(value = { "/pedido/" }, method = RequestMethod.POST)
	public String salvarPedido(@RequestBody PedidoAndroid pedidoAndroid) {
		System.out.println(pedidoAndroid.toString());
		Date dataAtual = new Date();
		Pedido pedido = new Pedido();
		pedido.setValores(pedidoAndroid.getValoresPedido());
		pedido.setUsuario(usuarioService.getById(new Integer(pedidoAndroid.getIdUsuario())));
		pedido.setReceita(receitaService.getById(new Integer(pedidoAndroid.getIdReceita())));
		pedido.setStatus(new Integer(pedidoAndroid.getStatus()));
		pedido.setData(dataAtual);
		pedido.setHora(dataAtual);
		pedido.setLancamentosPedido(pedido.getStatus(), pedido.getUsuario());
		pedidoService.save(pedido);
		return pedido.getId().toString();
	}

	@RequestMapping(value = { "/pedidos/{id}/" }, method = RequestMethod.GET)
	public ArrayList<Pedido> pedidosDoUsuario(@PathVariable Integer id) {
		Usuario usuario = usuarioService.getById(id);
		return (ArrayList<Pedido>) pedidoService.findByUsuarioAndData(usuario, new Date());
	}

	@RequestMapping(value = { "/login/" }, method = RequestMethod.GET)
	public Boolean login() {
		if (getPrincipal().equals(""))
		{
			return false;
		}
		return true;
	}

	@RequestMapping(value = { "/pagar/cartao" }, method = RequestMethod.POST)
	public RespostaVenda pagarPedido(@RequestBody PagamentoAndroid pagamentoAndroid) {
		// ...
		// Configure seu merchant
		System.out.println(pagamentoAndroid.toString());
		DadosCielo merchant = new DadosCielo();
		AmbienteCielo ambienteCielo = new AmbienteCielo();
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		for (String idPedido : pagamentoAndroid.getIdPedido()) {
			pedidos.add(pedidoService.getById(new Integer(idPedido)));
		}
		Comanda comanda = new Comanda(usuarioService.getById(new Integer(pagamentoAndroid.getIdUsuario())), pedidos);
		comandaService.save(comanda);

		// Crie uma instância de Sale informando o ID do pagamento
		Venda sale = new Venda(comanda.getId().toString(),
				usuarioService.getById(new Integer(pagamentoAndroid.getIdUsuario())));

		// Crie uma instância de Payment informando o valor do pagamento
		sale.setPayment(new Payment(comanda.getTotal().intValue()));
		Payment payment = sale.getPayment();

		// Crie uma instância de Credit Card utilizando os dados de teste
		// esses dados estão disponíveis no manual de integração
		// if (ValidaCartao.Validar(cartaoDeCredito.getNumeroCartao())) {
		payment.creditCard(pagamentoAndroid.getCodigoSeguranca(),
				ValidaCartao.badeira(pagamentoAndroid.getNumeroCartao()));
		payment.getCreditCard().setDataVencimento(pagamentoAndroid.getDataVencimento());
		payment.getCreditCard().setNumeroCartao(pagamentoAndroid.getNumeroCartao());
		payment.getCreditCard().setPortador(pagamentoAndroid.getPortador());
		// }
		RespostaVenda resposta = null;

		// Crie o pagamento na Cielo
		try {
			// Configure o SDK com seu merchant e o ambiente apropriado para
			// criar a venda
			sale = new CieloEcommerce(merchant, ambienteCielo).createSale(sale);

			// Com a venda criada na Cielo, já temos o ID do pagamento, TID e
			// demais
			// dados retornados pela Cielo
			String paymentId = sale.getPayment().getPaymentId();

			// Com o ID do pagamento, podemos fazer sua captura, se ela não
			// tiver sido capturada ainda
			resposta = new CieloEcommerce(merchant, ambienteCielo).captureSale(paymentId, 15700, 0);

		} catch (CieloRequestException e) {
			// Em caso de erros de integração, podemos tratar o erro aqui.
			// os códigos de erro estão todos disponíveis no manual de
			// integração.
			CieloError error = null;
			error = e.getError();
			System.out.println(error.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resposta;
	}

	private String getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(principal.toString());
		if (principal instanceof UserDetails) {
			Usuario usuario = usuarioService.findByCPF(((UserDetails) principal).getUsername());
			return usuario.getFirstName();
		} else {
			return "";
		}
	}
}
