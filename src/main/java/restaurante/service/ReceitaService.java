package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.ReceitaDao;
import restaurante.model.Receita;

@Service
public class ReceitaService {
	@Autowired
	ReceitaDao receitaDao;

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

}
