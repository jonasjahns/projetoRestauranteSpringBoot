package restaurante.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurante.model.Comanda;
import restaurante.model.LancamentoEstoque;
import restaurante.model.Pedido;
import restaurante.model.Receita;
import restaurante.model.Usuario;
import restaurante.model.ValorPedido;
import restaurante.pagamentos.AmbienteCielo;
import restaurante.pagamentos.CartaoDeCredito;
import restaurante.pagamentos.CieloEcommerce;
import restaurante.pagamentos.CieloError;
import restaurante.pagamentos.CieloRequestException;
import restaurante.pagamentos.DadosCielo;
import restaurante.pagamentos.Payment;
import restaurante.pagamentos.RespostaVenda;
import restaurante.pagamentos.ValidaCartao;
import restaurante.pagamentos.Venda;
import restaurante.service.ComandaService;
import restaurante.service.PedidoService;
import restaurante.service.ReceitaService;
import restaurante.service.RegistroEstoqueService;
import restaurante.service.TipoLancamentoEstoqueService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

	@Autowired
	UsuarioService userService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ReceitaService receitaService;

	@Autowired
	RegistroEstoqueService registroEstoqueService;

	@Autowired
	TipoLancamentoEstoqueService tipoLancamentoEstoqueService;

	@Autowired
	ComandaService comandaService;

	@RequestMapping(value = { "/principal" }, method = RequestMethod.GET)
	public String listarPedido(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Receita> receitas = receitaService.receitasDisponiveis();
		model.addAttribute("receitas", receitas);
		return "caixa";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.POST)
	public String novaComanda(ModelMap model) {
		Comanda comanda = new Comanda();
		comanda.setUsuario(getUsuario());
		comanda.setDataInicial(new Date());
		comanda.setHoraInicial(new Date());
		comandaService.save(comanda);
		model.addAttribute("comanda", comanda);
		model.addAttribute("loggedinuser", getPrincipal());
		return "comanda";
	}

	@RequestMapping(value = { "/{comandaId}/" }, method = RequestMethod.GET)
	public String comandaAtual(ModelMap model, @PathVariable Integer comandaId) {
		Comanda comanda = comandaService.getById(comandaId);
		model.addAttribute("comanda", comanda);
		model.addAttribute("loggedinuser", getPrincipal());
		return "comanda";
	}

	@RequestMapping(value = { "/{comandaId}/listar" }, method = RequestMethod.GET)
	public String receitasDisponiveis(ModelMap model, @PathVariable Integer comandaId) {
		Comanda comanda = comandaService.getById(comandaId);
		model.addAttribute("comanda", comanda);
		List<Receita> receitas = receitaService.receitasDisponiveis();
		model.addAttribute("receitas", receitas);
		model.addAttribute("loggedinuser", getPrincipal());
		return "disponiveis";
	}

	@RequestMapping(value = { "/{comandaId}/adicionar/{receitaId}" }, method = RequestMethod.GET)
	public String novoPedido(ModelMap model, @PathVariable Integer comandaId, @PathVariable Integer receitaId) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = null;
		for (Receita aux : receitaService.receitasDisponiveis()) {
			if (receitaId.equals(aux.getId())) {
				receita = aux;
			}
		}
		Comanda comanda = comandaService.getById(comandaId);
		Pedido pedido = new Pedido();
		model.addAttribute("receita", receita);
		model.addAttribute("pedido", pedido);
		model.addAttribute("comanda", comanda);
		model.addAttribute("loggedinuser", getPrincipal());
		return "cadastrapedido";
	}

	@RequestMapping(value = { "/{comandaId}/adicionar/{receitaId}" }, method = RequestMethod.POST)
	public String salvarPedido(@Valid Pedido pedido, BindingResult resultado, ModelMap model,
			@PathVariable Integer receitaId, @PathVariable Integer comandaId) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			Receita receita = receitaService.getById(receitaId);
			model.addAttribute("receita", receita);
			return "adicionar";
		}
		Date dataAtual = new Date();
		pedido.setData(dataAtual);
		pedido.setHora(dataAtual);
		pedido.setUsuario(getUsuario());
		pedido.setLancamentosPedido(pedido.getStatus(), getUsuario());
		Comanda comanda = comandaService.getById(comandaId);
		comanda.getPedidos().add(pedido);
		comandaService.save(comanda);
		salvarLancamentosEstoque(pedido.getValores(), dataAtual, 52);
		return "redirect:/caixa/" + comanda.getId() + "/listar/";
	}

	@RequestMapping(value = { "/{comandaId}/pagar/cartao" }, method = RequestMethod.GET)
	public String paginaPagamentoPedido(ModelMap model, @PathVariable Integer comandaId) {
		model.addAttribute("loggedinuser", getPrincipal());
		Comanda comanda = comandaService.getById(comandaId);
		model.addAttribute("comanda", comanda);
		model.addAttribute("cartaoDeCredito", new CartaoDeCredito());
		return "pagar";
	}

	@RequestMapping(value = { "/{comandaId}/pagar/cartao" }, method = RequestMethod.POST)
	public String pagarPedido(ModelMap model, @PathVariable Integer comandaId, CartaoDeCredito cartaoDeCredito) {
		// ...
		// Configure seu merchant
		DadosCielo merchant = new DadosCielo();
		AmbienteCielo ambienteCielo = new AmbienteCielo();

		// Crie uma instância de Sale informando o ID do pagamento
		Venda sale = new Venda(comandaId.toString(), getUsuario());

		// Crie uma instância de Payment informando o valor do pagamento
		sale.setPayment(new Payment(comandaService.getById(comandaId).getTotal().intValue()));
		Payment payment = sale.getPayment();

		// Crie uma instância de Credit Card utilizando os dados de teste
		// esses dados estão disponíveis no manual de integração
		// if (ValidaCartao.Validar(cartaoDeCredito.getNumeroCartao())) {
		payment.creditCard(cartaoDeCredito.getCodigoSeguranca(),
				ValidaCartao.badeira(cartaoDeCredito.getNumeroCartao()));
		payment.getCreditCard().setDataVencimento(cartaoDeCredito.getDataVencimento());
		payment.getCreditCard().setNumeroCartao(cartaoDeCredito.getNumeroCartao());
		payment.getCreditCard().setPortador(cartaoDeCredito.getPortador());
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
		System.out.println(resposta.toString());
		model.addAttribute("resposta", resposta);
		return "pago";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarPedido(@Valid Pedido pedido, BindingResult resultado, ModelMap model,
			@PathVariable Long id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			return "cadastrapedido";
		}

		pedidoService.save(pedido);
		return "redirect:/caixa/princiapl";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarPedido(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Pedido pedido = pedidoService.getById(id);
		Receita receita = receitaService.getById(pedido.getReceita().getId());
		model.addAttribute("pedido", pedido);
		model.addAttribute("receita", receita);
		model.addAttribute("editar", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "cadastrapedido";
	}

	private String getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			Usuario usuario = userService.findByCPF(((UserDetails) principal).getUsername());
			return usuario.getFirstName();
		} else {
			return "";
		}
	}

	private Usuario getUsuario() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			Usuario usuario = userService.findByCPF(((UserDetails) principal).getUsername());
			return usuario;
		} else {
			return null;
		}
	}

	public void salvarLancamentosEstoque(List<ValorPedido> valoresPedidos, Date dataAtual, Integer codigo) {
		for (ValorPedido valorPedido : valoresPedidos) {
			LancamentoEstoque lancamentoEstoque = new LancamentoEstoque();
			lancamentoEstoque.setData(dataAtual);
			lancamentoEstoque.setValor(1D);
			lancamentoEstoque.setQuantidade((new Double(valorPedido.getValor())));
			lancamentoEstoque.setTipo(tipoLancamentoEstoqueService.getByCodigo(52));
			registroEstoqueService.adicionarLancamento(lancamentoEstoque, valorPedido.getIngrediente(),
					valorPedido.getMedida());

		}
	}

}
