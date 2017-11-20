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

import restaurante.model.Ingrediente;
import restaurante.model.Medida;
import restaurante.model.Usuario;
import restaurante.service.IngredienteService;
import restaurante.service.MedidaService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

	@Autowired
	UsuarioService userService;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	MedidaService medidaService;

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarIngredientes(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Ingrediente> ingredientes = ingredienteService.getAll();
		model.addAttribute("ingredientes", ingredientes);
		return "todosingredientes";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
	public String novoIngrediente(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Ingrediente ingrediente = new Ingrediente();
		List<Medida> todasMedidas = medidaService.getAll();
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("editar", false);
		model.addAttribute("todasMedidas", todasMedidas);
		return "cadastraingrediente";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.POST)
	public String salvarIngrediente(@Valid Ingrediente ingrediente, BindingResult resultado, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Medida> todasMedidas = medidaService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			return "cadastraingrediente";
		}
		ingredienteService.save(ingrediente);
		return "redirect:/ingrediente/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarIngrediente(@Valid Ingrediente ingrediente, BindingResult resultado, ModelMap model,
			@PathVariable Long id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Medida> todasMedidas = medidaService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			return "cadastraingrediente";
		}

		ingredienteService.save(ingrediente);
		return "redirect:/ingrediente/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarIngrediente(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Medida> todasMedidas = medidaService.getAll();
		Ingrediente ingrediente = ingredienteService.getById(id);
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("editar", true);
		model.addAttribute("todasMedidas", todasMedidas);
		return "cadastraingrediente";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarIngrediente(@PathVariable Integer id) {
		ingredienteService.remover(id);
		return "redirect:/ingrediente/listar";
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
