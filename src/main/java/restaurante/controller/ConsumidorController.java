package restaurante.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurante.model.Usuario;
import restaurante.service.PedidoService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/")
public class ConsumidorController {

	@Autowired
	UsuarioService userService;

	@Autowired
	PedidoService pedidoService;

	@RequestMapping(value = { "/home", "/home/" }, method = RequestMethod.GET)
	public String meusServicos(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "servicos";
	}

	@RequestMapping(value = { "/meuspedidos/", "/meuspedidos" }, method = RequestMethod.GET)
	public String meusPedidos(ModelMap model) {
		model.addAttribute("pedidos", pedidoService.findByUsuarioAndData(getUsuario(), new Date()));
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "meuspedidos";
	}

	@RequestMapping(value = { "/meuspedidos/", "/meuspedidos" }, method = RequestMethod.POST)
	public String pedidosPorMesStatus(ModelMap model, @Valid Date data) {
		model.addAttribute("pedidos", pedidoService.findByUsuarioAndData(getUsuario(), data));
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "meuspedidos";
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
}
