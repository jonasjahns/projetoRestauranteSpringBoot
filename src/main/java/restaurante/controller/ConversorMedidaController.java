package restaurante.controller;

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

import restaurante.model.ConversorMedidas;
import restaurante.model.Ingrediente;
import restaurante.model.Medida;
import restaurante.model.Usuario;
import restaurante.service.ConversorMedidasService;
import restaurante.service.IngredienteService;
import restaurante.service.MedidaService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/conversor")
public class ConversorMedidaController {

	@Autowired
	UsuarioService userService;

	@Autowired
	ConversorMedidasService conversorMedidasService;

	@Autowired
	MedidaService medidaService;

	@Autowired
	IngredienteService ingredienteService;

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarConversoresMedidas(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<ConversorMedidas> todosConversorMedidas = conversorMedidasService.getAll();
		model.addAttribute("todosConversormedidas", todosConversorMedidas);
		return "todosconversor";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
	public String novoConversor(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		ConversorMedidas conversorMedidas = new ConversorMedidas();
		List<Ingrediente> todosIngredientes = ingredienteService.getAll();
		model.addAttribute("todosIngredientes", todosIngredientes);
		List<Medida> todasMedidas = medidaService.getAll();
		model.addAttribute("todasMedidas", todasMedidas);
		model.addAttribute("conversorMedidas", conversorMedidas);
		model.addAttribute("editar", false);
		return "cadastraconversor";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.POST)
	public String salvarConversorMedidas(@Valid ConversorMedidas conversorMedidas, BindingResult resultado,
			ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Ingrediente> todosIngredientes = ingredienteService.getAll();
			model.addAttribute("todosIngredientes", todosIngredientes);
			List<Medida> todasMedidas = medidaService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			return "cadastraconversor";
		}
		conversorMedidasService.save(conversorMedidas);
		return "redirect:/conversor/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarConversorMedidas(@Valid ConversorMedidas conversorMedidas, BindingResult resultado,
			ModelMap model, @PathVariable Long id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Ingrediente> todosIngredientes = ingredienteService.getAll();
			model.addAttribute("todosIngredientes", todosIngredientes);
			List<Medida> todasMedidas = medidaService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			return "cadastramedida";
		}
		conversorMedidasService.save(conversorMedidas);
		return "redirect:/conversor/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarConversorMedidas(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		ConversorMedidas conversorMedidas = conversorMedidasService.getById(id);
		model.addAttribute("conversorMedidas", conversorMedidas);
		List<Ingrediente> todosIngredientes = ingredienteService.getAll();
		model.addAttribute("todosIngredientes", todosIngredientes);
		List<Medida> todasMedidas = medidaService.getAll();
		model.addAttribute("todasMedidas", todasMedidas);
		model.addAttribute("editar", true);
		return "cadastraconversor";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarConversorMedidas(@PathVariable Integer id) {
		conversorMedidasService.remover(id);
		return "redirect:/conversor/listar";
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
