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

import restaurante.model.Medida;
import restaurante.model.Usuario;
import restaurante.service.MedidaService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/medida")
public class MedidaController {

	@Autowired
	MedidaService medidaService;

	@Autowired
	UsuarioService userService;

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarMedidas(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Medida> medidas = medidaService.getAll();
		model.addAttribute("medidas", medidas);
		return "todasmedidas";
	}

	@RequestMapping(value = { "/nova" }, method = RequestMethod.GET)
	public String novaMedida(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Medida medida = new Medida();
		model.addAttribute("medida", medida);
		model.addAttribute("editar", false);
		return "cadastramedida";
	}

	@RequestMapping(value = { "/nova" }, method = RequestMethod.POST)
	public String salvarMedida(@Valid Medida medida, BindingResult resultado, ModelMap model) {
		if (resultado.hasErrors()) {
			return "cadastramedida";
		}
		medidaService.save(medida);
		return "redirect:/medida/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarMedida(@Valid Medida medida, BindingResult resultado, ModelMap model,
			@PathVariable Long id) {
		if (resultado.hasErrors()) {
			model.addAttribute("loggedinuser", getPrincipal());
			return "cadastramedida";
		}
		medidaService.save(medida);
		return "redirect:/medida/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarMedida(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Medida medida = medidaService.getById(id);
		model.addAttribute("medida", medida);
		model.addAttribute("editar", true);
		return "cadastramedida";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarMedida(@PathVariable Integer id) {
		medidaService.remover(id);
		return "redirect:/medida/listar";
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
