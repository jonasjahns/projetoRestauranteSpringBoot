package restaurante.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import restaurante.model.Pedido;
import restaurante.model.Usuario;
import restaurante.service.PedidoService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/cozinha")
public class CozinhaController {

	@Autowired
	PedidoService pedidoService;

	@Autowired
	UsuarioService userService;

	@RequestMapping(value = { "/pedidos" }, method = RequestMethod.GET)
	private @ResponseBody String listarPedidos() {
		List<Pedido> pedidos = pedidoService.getAll();
		String json = new Gson().toJson(pedidos);
		return  json;
	}

	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	private String todosPedidosDoDia(ModelMap model) {
		Date data = new Date();
		List<Pedido> pedidosDoDia = pedidoService.findByData(data);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("pedidos", pedidosDoDia);
		model.addAttribute("teste", "teste");
		return "cozinha";
	}

	@RequestMapping(value = { "/pedido-{id}/atualizar/{status}" })
	private String atualizarStatusPedido(ModelMap model, @PathVariable Integer id, @PathVariable Integer status) {
		Pedido pedido = pedidoService.getById(id);
		pedido.setLancamentosPedido(status, getUsuario());
		pedidoService.save(pedido);
		return "redirect:/cozinha/listar";
	}

	@RequestMapping(value = { "/listar/{status}" }, method = RequestMethod.GET)
	private String pedidosDoDiaPorStauts(ModelMap model, @PathVariable Integer status) {
		Date data = new Date();
		List<Pedido> pedidos = pedidoService.findByDataAndStatus(data, status);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("pedidos", pedidos);
		return "cozinha";
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