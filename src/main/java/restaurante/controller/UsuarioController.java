package restaurante.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurante.model.Usuario;
import restaurante.model.UsuarioProfile;
import restaurante.service.UsuarioProfileService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UsuarioProfileService userProfileService;

	

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<Usuario> users = userService.getAll();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "todosusuarios";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/novo" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		Usuario user = new Usuario();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "cadastrausuario";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/novo" }, method = RequestMethod.POST)
	public String saveUser(@Valid Usuario user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		if (!userService.CPFUsuarioUnico(user.getId(), user.getCpf())) {
			FieldError ssoError = new FieldError("user", "CPF",
					messageSource.getMessage("non.unique.ssoId", new String[] { user.getCpf() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		userService.save(user);
		return "redirect:/usuario/listar";
	}

	@RequestMapping(value = { "/editar/{CPF}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String CPF, ModelMap model) {
		Usuario user = userService.findByCPF(CPF);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "cadastrausuario";
	}

	@RequestMapping(value = { "/editar/{CPF}" }, method = RequestMethod.POST)
	public String updateUser(@Valid Usuario user, BindingResult result, ModelMap model, @PathVariable String CPF) {

		if (result.hasErrors()) {
			return "cadastrausuario";
		}
		userService.save(user);
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/usuario/listar";
	}

	@RequestMapping(value = { "/deletar/{CPF}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String CPF) {
		userService.remover(CPF);
		return "redirect:/usuario/listar";
	}

	@ModelAttribute("roles")
	public List<UsuarioProfile> initializeProfiles() {
		return userProfileService.getAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */

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
