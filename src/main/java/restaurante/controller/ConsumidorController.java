package restaurante.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import restaurante.model.Pedido;
import restaurante.model.RangeDatas;
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
		Calendar cal = Calendar.getInstance();
		Date primeiroDia = new Date();
		cal.setTime(primeiroDia);
		cal.set(primeiroDia.getYear(), primeiroDia.getMonth(), 1);
		model.addAttribute("pedidos",
				pedidoService.findByUsuarioAndDataAfterAndDataBefore(getUsuario(), cal.getTime(), new Date()));
		RangeDatas rangeDatas = new RangeDatas(cal.getTime(), new Date());
		model.addAttribute("rangedatas", rangeDatas);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "meuspedidos";
	}

	@RequestMapping(value = { "/meuspedidos/", "/meuspedidos" }, method = RequestMethod.POST)
	public String pedidosPorMesStatus(ModelMap model, @Valid RangeDatas rangeDatas, BindingResult resultado) {
		if (resultado.hasErrors()) {
			System.out.println(resultado.toString());
		}
		model.addAttribute("pedidos", pedidoService.findByUsuarioAndDataAfterAndDataBefore(getUsuario(),
				rangeDatas.dataDe, rangeDatas.dataAte));
		model.addAttribute("rangedatas", rangeDatas);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "meuspedidos";
	}

	@RequestMapping(value = { "/meuspedidos/cancelar/{id}" })
	public String cancelarPedidoUsuario(ModelMap model, @PathVariable Integer id) {
		Pedido pedido = pedidoService.getById(id);
		pedido.setStatus(5);
		pedidoService.save(pedido);
		return "redirect:/meuspedidos/";
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}
