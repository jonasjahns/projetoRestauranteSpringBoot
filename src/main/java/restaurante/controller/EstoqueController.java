package restaurante.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurante.model.Ingrediente;
import restaurante.model.LancamentoEstoque;
import restaurante.model.Medida;
import restaurante.model.RegistroEstoque;
import restaurante.model.Usuario;
import restaurante.service.IngredienteService;
import restaurante.service.MedidaService;
import restaurante.service.RegistroEstoqueService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	UsuarioService userService;

	@Autowired
	RegistroEstoqueService registroEstoqueService;

	@Autowired
	MedidaService medidaService;

	@Autowired
	IngredienteService ingredienteService;

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarEstoque(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<RegistroEstoque> registrosEstoque = registroEstoqueService.getAll();
		model.addAttribute("registrosEstoque", registrosEstoque);
		return "estoque";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
	public String novoRegistroEstoque(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		RegistroEstoque registroEstoque = new RegistroEstoque();
		List<Medida> todasMedidas = medidaService.getAll();
		List<Ingrediente> todosIngredientes = ingredienteService.getAll();
		model.addAttribute("registroEstoque", registroEstoque);
		model.addAttribute("todasMedidas", todasMedidas);
		model.addAttribute("todosIngredientes", todosIngredientes);
		model.addAttribute("editar", false);
		return "cadastraregistroestoque";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.POST)
	public String salvarRegistroEstoque(@Valid RegistroEstoque registroEstoque, BindingResult resultado,
			ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Medida> todasMedidas = medidaService.getAll();
			List<Ingrediente> todosIngredientes = ingredienteService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			model.addAttribute("todosIngredientes", todosIngredientes);
			return "cadastraregistroestoque";
		}
		registroEstoqueService.save(registroEstoque);
		return "redirect:/estoque/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarRegistroEstoque(@Valid RegistroEstoque registroEstoque, BindingResult resultado,
			ModelMap model, @PathVariable Long id) {
		if (resultado.hasErrors()) {
			return "cadastraregistroestoque";
		}
		registroEstoqueService.save(registroEstoque);
		return "redirect:/estoque/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarRegistroEstoque(@PathVariable Integer id, ModelMap model) {
		RegistroEstoque registroEstoque = registroEstoqueService.getById(id);
		List<Medida> todasMedidas = medidaService.getAll();
		List<Ingrediente> todosIngredientes = ingredienteService.getAll();
		model.addAttribute("todasMedidas", todasMedidas);
		model.addAttribute("todosIngredientes", todosIngredientes);
		model.addAttribute("registroEstoque", registroEstoque);
		model.addAttribute("editar", true);
		return "cadastraregistroestoque";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarRegistroEstoque(@PathVariable Integer id) {
		registroEstoqueService.remover(id);
		return "redirect:/estoque/listar";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = { "/lancamentos-{id}" }, method = RequestMethod.GET)
	public String lancamentosRegistroEstoque(@PathVariable Integer id, ModelMap model) {
		RegistroEstoque registroEstoque = registroEstoqueService.getById(id);
		model.addAttribute("registroEstoque", registroEstoque);
		return "lancamentos";
	}

	@RequestMapping(value = { "/lancamentos-{id}/novo" }, method = RequestMethod.GET)
	public String adicionarLancamentoEstoque(@PathVariable Integer id, ModelMap model) {
		RegistroEstoque registroEstoque = registroEstoqueService.getById(id);
		model.addAttribute("registroEstoque", registroEstoque);
		LancamentoEstoque lancamentoEstoque = new LancamentoEstoque();
		model.addAttribute("lancamentoEstoque", lancamentoEstoque);
		return "novolancamento";
	}

	@RequestMapping(value = { "/lancamentos-{id}/novo" }, method = RequestMethod.POST)
	public String salvarLancamentoEstoque(@Valid LancamentoEstoque lancamentoEstoque, BindingResult resultado,
			ModelMap model, @PathVariable Integer id) {
		RegistroEstoque registroEstoque = registroEstoqueService.getById(id);
		if (resultado.hasErrors()) {
			model.addAttribute("registroEstoque", registroEstoque);
			return "novolancamento";
		}
		if (lancamentoEstoque.getTipo().getCodigo() < 50) {
			if (registroEstoque.getDisponiveis() < lancamentoEstoque.getQuantidade()) {
				model.addAttribute("registroEstoque", registroEstoque);
				return "novolancamento";
			}
		}
		registroEstoque.adicionarLancamento(lancamentoEstoque);
		return "redirect:/estoque/lancamentos-" + registroEstoque.getId();
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

}
