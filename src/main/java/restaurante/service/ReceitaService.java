package restaurante.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import restaurante.dao.ReceitaDao;
import restaurante.model.Ingrediente;
import restaurante.model.Medida;
import restaurante.model.Receita;
import restaurante.model.ReceitaGrupo;
import restaurante.model.RegistroEstoque;

@Service
public class ReceitaService {
	@Autowired
	ReceitaDao receitaDao;
	
	@Autowired
	RegistroEstoqueService registroEstoqueService;
	
	@Autowired
	ConversorMedidasService conversorMedidasService;

	public Receita getById(Integer id) {
		return receitaDao.findOne(id);
	}

	public List<Receita> getAll() {
		return (List<Receita>) receitaDao.findAll();
	}

	public void save(Receita receita) {
		receitaDao.save(receita);
	}

	public void remover(Integer id) {
		receitaDao.delete(id);
	}

	public List<Ingrediente> ingredientesDisponiveis(List<Ingrediente> ingredientes, Double valor, Medida medida) {
		List<Ingrediente> disponiveis = new ArrayList<Ingrediente>();
		List<RegistroEstoque> registrosEstoque = registroEstoqueService.getAll();
		for (Ingrediente ingrediente : ingredientes) {
			for (RegistroEstoque registroEstoque : registrosEstoque) {
				if (registroEstoque.getIngrediente().getId() == ingrediente.getId()) {
					Double taxaConversao = 1D;
					if (medida.getId() != registroEstoque.getMedida().getId()) {
						taxaConversao = conversorMedidasService.fatorConversao(medida, registroEstoque.getMedida());
					}
					if (registroEstoque.getDisponiveis() > valor * taxaConversao) {
						disponiveis.add(ingrediente);
					}
				}
			}
		}
		return disponiveis;
	}

	@Cacheable("receitas")
	public List<Receita> receitasDisponiveis() {
		List<Receita> disponiveis = new ArrayList<Receita>();
		for (Receita receita : this.getAll()) {
			Boolean disponivel = true;
			for (ReceitaGrupo receitaGrupo : receita.getReceitaGrupos()) {
				List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
				Double valor;
				if (receitaGrupo.getNivel() == 1) {
					valor = new Double(receitaGrupo.getVariacaoMedida());
				} else {
					valor = new Double(
							receitaGrupo.getValoresMedida().get((receitaGrupo.getValoresMedida().size() - 1)));
				}

				ingredientes = ingredientesDisponiveis(receitaGrupo.getGrupo().getIngredientes(), valor,
						receitaGrupo.getMedida());
				if (ingredientes.size() == 0) {
					disponivel = false;
				} else {
					receitaGrupo.getGrupo().setIngredientes(ingredientes);
				}
			}
			if (disponivel) {
				disponiveis.add(receita);
			}
		}
		return disponiveis;
	}

}
