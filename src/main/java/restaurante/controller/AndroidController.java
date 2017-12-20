package restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurante.model.Medida;
import restaurante.model.Receita;
import restaurante.service.MedidaService;
import restaurante.service.ReceitaService;

@RestController
@RequestMapping("/android/")
public class AndroidController {

	@Autowired
	ReceitaService receitaService;
	
	@Autowired
	MedidaService medidaService;

	@RequestMapping(value = { "/disponiveis/" }, method = RequestMethod.GET, produces = "application/json")
	public List<Receita> receitasDisponiveis() {
		return receitaService.receitasDisponiveis();
	}
	
	@RequestMapping(value = { "/medida/" }, method = RequestMethod.GET, produces = "application/json")
	public Medida medida() {
		return medidaService.getById(1);
	}

}
