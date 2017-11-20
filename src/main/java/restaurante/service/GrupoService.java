package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.GrupoDao;
import restaurante.model.Grupo;

@Service
public class GrupoService {

	@Autowired
	GrupoDao grupoDao;

	public Grupo getById(Integer id) {
		return grupoDao.findOne(id);
	}

	public List<Grupo> getAll() {
		return (List<Grupo>) grupoDao.findAll();
	}

	public void save(Grupo grupo) {
		grupoDao.save(grupo);
	}

	public void remover(Integer id) {
		grupoDao.delete(id);
	}

}
