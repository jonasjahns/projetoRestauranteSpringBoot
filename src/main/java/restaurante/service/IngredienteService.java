package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.IngredienteDao;
import restaurante.model.Ingrediente;

@Service
public class IngredienteService {

	@Autowired
	IngredienteDao ingredienteDao;

	public Ingrediente getById(Integer id) {
		return ingredienteDao.findOne(id);
	}

	public List<Ingrediente> getAll() {
		return (List<Ingrediente>) ingredienteDao.findAll();
	}

	public void save(Ingrediente ingrediente) {
		ingredienteDao.save(ingrediente);
	}

	public void remover(Integer id) {
		ingredienteDao.delete(id);
	}
}
