package restaurante.controller;

import java.util.ArrayList;
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
import restaurante.model.Ingrediente;
import restaurante.model.LancamentoEstoque;
import restaurante.model.Medida;
import restaurante.model.Pedido;
import restaurante.model.Receita;
import restaurante.model.ReceitaGrupo;
import restaurante.model.RegistroEstoque;
import restaurante.model.Usuario;
import restaurante.model.ValorPedido;
import restaurante.service.ComandaService;
import restaurante.service.ConversorMedidasService;
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
	ConversorMedidasService conversorMedidasService;

	@Autowired
	ComandaService comandaService;

	@RequestMapping(value = { "/princiapl" }, method = RequestMethod.GET)
	public String listarPedido(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Receita> receitas = receitasDisponiveis();
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
		return "comanda";
	}

	@RequestMapping(value = { "/{comandaId}/listar" }, method = RequestMethod.GET)
	public String comandaAtual(ModelMap model, @PathVariable Integer comandaId) {
		Comanda comanda = comandaService.getById(comandaId);
		model.addAttribute("comanda", comanda);
		return "comanda";
	}

	@RequestMapping(value = { "/{comandaId}/adicionar/{receitaId}" }, method = RequestMethod.GET)
	public String novoPedido(ModelMap model, @PathVariable Integer comandaId, @PathVariable Integer receitaId) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = null;
		for (Receita aux : receitasDisponiveis()) {
			if (receitaId.equals(aux.getId())) {
				receita = aux;
			}
		}
		Comanda comanda = comandaService.getById(comandaId);
		Pedido pedido = new Pedido();
		model.addAttribute("receita", receita);
		model.addAttribute("pedido", pedido);
		model.addAttribute("comanda", comanda);
		return "adicionar";
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

	private List<Ingrediente> ingredientesDisponiveis(List<Ingrediente> ingredientes, Double valor, Medida medida) {
		List<Ingrediente> disponiveis = new ArrayList<Ingrediente>();
		List<RegistroEstoque> registrosEstoque = registroEstoqueService.getAll();
		for (Ingrediente ingrediente : ingredientes) {
			for (RegistroEstoque registroEstoque : registrosEstoque) {
				if (registroEstoque.getIngrediente().getId() == ingrediente.getId()) {
					Double taxaConversao = 1D;
					if (medida.getId() != registroEstoque.getMedida().getId()) {
						taxaConversao = conversorMedidasService.fatorConversao(medida, registroEstoque.getMedida());
					}
					if (registroEstoque.getDisponiveis() > valor * taxaConversao) {
						disponiveis.add(ingrediente);
					}
				}
			}
		}
		return disponiveis;
	}

	private List<Receita> receitasDisponiveis() {
		List<Receita> disponiveis = new ArrayList<Receita>();
		for (Receita receita : receitaService.getAll()) {
			Boolean disponivel = true;
			for (ReceitaGrupo receitaGrupo : receita.getReceitaGrupos()) {
				List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
				Double valor;
				if (receitaGrupo.getNivel() == 1) {
					valor = new Double(receitaGrupo.getVariacaoMedida());
				} else {
					valor = new Double(receitaGrupo.getValoresMedida().get((receitaGrupo.getValoresMedida().size() - 1)));
				}

				ingredientes = ingredientesDisponiveis(receitaGrupo.getGrupo().getIngredientes(), valor,
						receitaGrupo.getMedida());
				if (ingredientes.size() == 0) {
					disponivel = false;
				} else {
					receitaGrupo.getGrupo().setIngredientes(ingredientes);
				}
			}
			if (disponivel) {
				disponiveis.add(receita);
			}
		}
		return disponiveis;
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
