package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.MedidaDao;
import restaurante.model.Medida;

@Service
public class MedidaService {

	@Autowired
	private MedidaDao medidaDao;

	public Medida getById(Integer id) {
		return medidaDao.findOne(id);
	}

	public List<Medida> getAll() {
		return (List<Medida>) medidaDao.findAll();
	}

	public void save(Medida medida) {
		medidaDao.save(medida);
	}

	public void remover(Integer id) {
		medidaDao.delete(id);
	}
}
