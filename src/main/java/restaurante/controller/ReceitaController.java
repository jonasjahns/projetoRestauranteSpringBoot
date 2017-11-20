package restaurante.controller;

import java.util.ArrayList;
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
import restaurante.model.GrupoList;
import restaurante.model.Ingrediente;
import restaurante.model.Medida;
import restaurante.model.Receita;
import restaurante.model.Usuario;
import restaurante.service.GrupoService;
import restaurante.service.IngredienteService;
import restaurante.service.MedidaService;
import restaurante.service.ReceitaGrupoService;
import restaurante.service.ReceitaService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	UsuarioService userService;

	@Autowired
	ReceitaService receitaService;

	@Autowired
	MedidaService medidaService;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	GrupoService grupoService;

	@Autowired
	ReceitaGrupoService receitaGrupoService;

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listarReceita(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<Receita> receitas = receitaService.getAll();
		model.addAttribute("receitas", receitas);
		return "todasreceitas";
	}

	@RequestMapping(value = { "/nova" }, method = RequestMethod.GET)
	public String novaReceita(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = new Receita();
		model.addAttribute("receita", receita);
		model.addAttribute("editar", false);
		return "cadastrareceita";
	}

	@RequestMapping(value = { "/nova" }, method = RequestMethod.POST)
	public String salvarReceita(@Valid Receita receita, BindingResult resultado, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			return "cadastrareceita";
		}

		receitaService.save(receita);
		return "redirect:/receita/" + receita.getId() + "/grupos/";
	}

	@RequestMapping(value = { "/{id}/grupos/" }, method = RequestMethod.GET)
	public String novoGrupoReceita(ModelMap model, @PathVariable Integer id) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = receitaService.getById(id);
		model.addAttribute("receita", receita);
		return "escolhermetodo";
	}

	@RequestMapping(value = { "/{id}/grupos/novo" }, method = RequestMethod.GET)
	public String criarGrupoReceita(ModelMap model, @PathVariable Integer id) {
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

	@RequestMapping(value = { "/{id}/grupos/novo" }, method = RequestMethod.POST)
	public String salvarGrupoReceita(@Valid Grupo grupo, ModelMap model, @PathVariable Integer id) {
		model.addAttribute("loggedinuser", getPrincipal());
		grupoService.save(grupo);
		Receita receita = receitaService.getById(id);
		ReceitaGrupo receitaGrupo = new ReceitaGrupo();
		receitaGrupo.setGrupo(grupo);
		receita.getReceitaGrupos().add(receitaGrupo);
		receitaService.save(receita);
		return "redirect:/receita/" + receita.getId() + "/grupos/";
	}

	@RequestMapping(value = { "/{id}/grupos/selecionar" }, method = RequestMethod.GET)
	public String selecionarGruposReceita(ModelMap model, @PathVariable Integer id) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = receitaService.getById(id);
		List<Grupo> todosGrupos = grupoService.getAll();
		GrupoList gruposSelecionados = new GrupoList();
		model.addAttribute("todosGrupos", todosGrupos);
		model.addAttribute("gruposSeleciondos", gruposSelecionados);
		model.addAttribute("receita", receita);
		return "selecionargrupo";
	}

	@RequestMapping(value = { "/{id}/grupos/selecionar" }, method = RequestMethod.POST)
	public String selecionarGrupoReceita(@Valid GrupoList gruposSelecionados, BindingResult resultado, ModelMap model,
			@PathVariable Integer id) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<ReceitaGrupo> receitaGrupos = new ArrayList<ReceitaGrupo>();
		for (Grupo grupo : gruposSelecionados.getGrupoList()) {
			ReceitaGrupo receitaGrupo = new ReceitaGrupo();
			receitaGrupo.setGrupo(grupo);
			receitaGrupos.add(receitaGrupo);
		}
		Receita receita = receitaService.getById(id);
		receita.getReceitaGrupos().addAll(receitaGrupos);
		receitaService.save(receita);
		model.addAttribute("receita", receita);
		return "redirect:/receita/" + receita.getId() + "/grupos/";
	}

	@RequestMapping(value = { "/{id}/grupos/configurar" }, method = RequestMethod.GET)
	public String configurarGruposReceita(ModelMap model, @PathVariable Integer id) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = receitaService.getById(id);
		model.addAttribute("receita", receita);
		return "configuragruporeceita";
	}

	@RequestMapping(value = { "/{id}/grupos/configurar" }, method = RequestMethod.POST)
	public String salvarGruposReceita(@Valid Receita receita, BindingResult resultado, ModelMap model,
			@PathVariable Integer id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			Receita receitaTemporaria = receitaService.getById(id);
			model.addAttribute("receita", receitaTemporaria);
			return "configuragruporeceita";
		}
		receitaService.save(receita);
		return "redirect:/receita/listar";
	}

	@RequestMapping(value = { "/{idReceita}/grupos/deletar-{idGrupo}" })
	public String removerGrupoReceita(ModelMap model, @PathVariable Integer idReceita, @PathVariable Integer idGrupo) {
		model.addAttribute("loggedinuser", getPrincipal());
		Grupo grupo = grupoService.getById(idGrupo);
		Receita receita = receitaService.getById(idReceita);
		ReceitaGrupo encontrado = new ReceitaGrupo();
		for (ReceitaGrupo receitaGrupo : receita.getReceitaGrupos()) {
			if (receitaGrupo.getGrupo().getId() == grupo.getId()) {
				encontrado = receitaGrupo;
			}
		}
		receita.getReceitaGrupos().remove(encontrado);
		receitaService.save(receita);
		if (encontrado.getMedida() == null) {
			receitaGrupoService.remover(encontrado.getId());
			grupoService.remover(idGrupo);
		}
		return "redirect:/receita/" + receita.getId() + "/grupos/";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.POST)
	public String atualizarReceita(@Valid Receita receita, BindingResult resultado, ModelMap model,
			@PathVariable Long id) {
		model.addAttribute("loggedinuser", getPrincipal());
		if (resultado.hasErrors()) {
			return "cadastrareceita";
		}

		receitaService.save(receita);
		return "redirect:/receita/listar";
	}

	@RequestMapping(value = { "/editar-{id}" }, method = RequestMethod.GET)
	public String editarReceita(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		Receita receita = receitaService.getById(id);
		model.addAttribute("receita", receita);
		model.addAttribute("editar", true);
		return "cadastrareceita";
	}

	@RequestMapping(value = { "/deletar-{id}" }, method = RequestMethod.GET)
	public String deletarReceita(@PathVariable Integer id) {
		receitaService.remover(id);
		return "redirect:/receita/listar";
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
