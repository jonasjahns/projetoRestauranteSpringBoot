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

import restaurante.model.Grupo;
import restaurante.model.Ingrediente;
import restaurante.model.Medida;
import restaurante.model.Usuario;
import restaurante.service.GrupoService;
import restaurante.service.IngredienteService;
import restaurante.service.MedidaService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	UsuarioService userService;

	@Autowired
	MedidaService medidaService;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	GrupoService grupoService;

	private String getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			Usuario usuario = userService.findByCPF(((UserDetails) principal).getUsername());
			return usuario.getFirstName();
		} else {
			return "";
		}
	}

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarGrupos(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Grupo> grupos = grupoService.getAll();
		model.addAttribute("grupos", grupos);
		return "todosgrupos";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
	public String novoGrupo(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Grupo grupo = new Grupo();
		List<Ingrediente> todosIngredientes = ingredienteService.getAll();
		List<Medida> todasMedidas = medidaService.getAll();
		model.addAttribute("todasMedidas", todasMedidas);
		model.addAttribute("grupo", grupo);
		model.addAttribute("editar", false);
		model.addAttribute("todosIngredientes", todosIngredientes);
		return "cadastragrupo";
	}

	@RequestMapping(value = { "/novo" }, method = RequestMethod.POST)
	public String salvarGrupo(@Valid Grupo grupo, BindingResult resultado, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Ingrediente> todosIngredientes = ingredienteService.getAll();
			List<Medida> todasMedidas = medidaService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			model.addAttribute("todosIngredientes", todosIngredientes);
			return "cadastragrupo";
		}
		grupoService.save(grupo);
		return "redirect:/grupo/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarGrupo(@Valid Grupo grupo, BindingResult resultado, ModelMap model, @PathVariable Long id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			List<Ingrediente> todosIngredientes = ingredienteService.getAll();
			List<Medida> todasMedidas = medidaService.getAll();
			model.addAttribute("todasMedidas", todasMedidas);
			model.addAttribute("todosIngredientes", todosIngredientes);
			return "cadastragrupo";
		}
		grupoService.save(grupo);
		return "redirect:/grupo/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarGrupo(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Ingrediente> todosIngredientes = ingredienteService.getAll();
		List<Medida> todasMedidas = medidaService.getAll();
		Grupo grupo = grupoService.getById(id);
		model.addAttribute("grupo", grupo);
		model.addAttribute("editar", true);
		model.addAttribute("todosIngredientes", todosIngredientes);
		model.addAttribute("todasMedidas", todasMedidas);
		return "cadastragrupo";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarGrupo(@PathVariable Integer id) {
		grupoService.remover(id);
		return "redirect:/grupo/listar";
	}

}
