package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.ReceitaGrupoDao;
import restaurante.model.ReceitaGrupo;

@Service
public class ReceitaGrupoService {
	@Autowired
	ReceitaGrupoDao receitaGrupoDao;

	public ReceitaGrupo getById(Integer id) {
		return receitaGrupoDao.findOne(id);
	}

	public List<ReceitaGrupo> getAll() {
		return (List<ReceitaGrupo>) receitaGrupoDao.findAll();
	}

	public void save(ReceitaGrupo receitaGrupo) {
		receitaGrupoDao.save(receitaGrupo);
	}

	public void remover(Integer id) {
		receitaGrupoDao.delete(id);
	}
}
