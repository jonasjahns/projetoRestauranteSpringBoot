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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurante.model.Pedido;
import restaurante.model.RangeDatas;
import restaurante.model.Usuario;
import restaurante.service.PedidoService;
import restaurante.service.UsuarioService;

@Controller
@RequestMapping("/relatorios/")
public class RelatorioController {

	@Autowired
	UsuarioService userService;

	@Autowired
	PedidoService pedidoService;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String meusServicos(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "relatorios";
	}
	
	@RequestMapping(value = { "/vendas" }, method = RequestMethod.GET)
	public String configuraRelatorioDeVendas(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		model.addAttribute("rangeDatas", new RangeDatas());
		return "relatoriovendas";
	}

	@RequestMapping(value = { "/vendas" }, method = RequestMethod.POST)
	public String relatorioDeVendas(ModelMap model, @Valid RangeDatas rangeDatas) {
		List<Pedido> pedidosFiltrados = pedidoService.findByDataBetween(rangeDatas.getDataDe(), rangeDatas.getDataAte());
		model.addAttribute("pedidos", pedidosFiltrados);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("usuario", getUsuario());
		return "relatoriovendas";
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
