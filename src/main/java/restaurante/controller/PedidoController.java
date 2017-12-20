package restaurante.controller;

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

import restaurante.model.LancamentoEstoque;
import restaurante.model.Pedido;
import restaurante.model.Receita;
import restaurante.model.Usuario;
import restaurante.model.ValorPedido;
import restaurante.service.ConversorMedidasService;
import restaurante.service.PedidoService;
import restaurante.service.ReceitaService;
import restaurante.service.RegistroEstoqueService;
import restaurante.service.TipoLancamentoEstoqueService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

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
	ConversorMedidasService conversorMedidasService;

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarPedido(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Receita> receitas = receitaService.receitasDisponiveis();
		model.addAttribute("receitas", receitas);
		return "todospedidos";
	}

	@RequestMapping(value = { "/novo/{receitaId}" }, method = RequestMethod.GET)
	public String novoPedido(ModelMap model, @PathVariable Integer receitaId) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = null;
		for (Receita aux : receitaService.receitasDisponiveis()) {
			if (receitaId.equals(aux.getId())) {
				receita = aux;
			}
		}
		Pedido pedido = new Pedido();
		model.addAttribute("receita", receita);
		model.addAttribute("pedido", pedido);
		return "cadastrapedido";
	}

	@RequestMapping(value = { "/novo/{receitaId}" }, method = RequestMethod.POST)
	public String salvarPedido(@Valid Pedido pedido, BindingResult resultado, ModelMap model,
			@PathVariable Integer receitaId) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			Receita receita = receitaService.getById(receitaId);
			model.addAttribute("receita", receita);
			return "cadastrapedido";
		}
		Date dataAtual = new Date();
		pedido.setData(dataAtual);
		pedido.setHora(dataAtual);
		pedido.setUsuario(getUsuario());
		pedido.setLancamentosPedido(pedido.getStatus(), getUsuario());
		pedidoService.save(pedido);
		for (ValorPedido valorPedido : pedido.getValores()) {
			LancamentoEstoque lancamentoEstoque = new LancamentoEstoque();
			lancamentoEstoque.setData(dataAtual);
			lancamentoEstoque.setValor(1D);
			lancamentoEstoque.setQuantidade((new Double(valorPedido.getValor())));
			lancamentoEstoque.setTipo(tipoLancamentoEstoqueService.getByCodigo(52));
			registroEstoqueService.adicionarLancamento(lancamentoEstoque, valorPedido.getIngrediente(),
					valorPedido.getMedida());

		}
		return "redirect:/pedido/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarPedido(@Valid Pedido pedido, BindingResult resultado, ModelMap model,
			@PathVariable Long id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			return "cadastrapedido";
		}

		pedidoService.save(pedido);
		return "redirect:/pedido/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarPedido(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Pedido pedido = pedidoService.getById(id);
		Receita receita = receitaService.getById(pedido.getReceita().getId());
		model.addAttribute("pedido", pedido);
		model.addAttribute("receita", receita);
		model.addAttribute("editar", true);
		return "cadastrapedido";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarPedido(@PathVariable Integer id) {
		pedidoService.remover(id);
		return "redirect:/pedido/listar";
	}

	@RequestMapping(value = { "/listar/{status}" }, method = RequestMethod.GET)
	public String listarPedidosPorStatus(@PathVariable Integer status, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Pedido> pedidos = pedidoService.getByStatus(status);
		for (Pedido pedido : pedidos) {
			System.out.println(pedido.toString());
		}
		model.addAttribute("pedidos", pedidos);
		return "pedidostatus";
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

	@RequestMapping(value = { "/teste/" }, method = RequestMethod.GET)
	public String testePostPedido(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "teste";
	}
}
