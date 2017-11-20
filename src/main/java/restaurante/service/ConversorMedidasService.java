package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.ConversorMedidasDao;
import restaurante.model.ConversorMedidas;
import restaurante.model.Medida;

@Service
public class ConversorMedidasService {
	@Autowired
	ConversorMedidasDao conversorMedidasDao;

	public ConversorMedidas getById(Integer id) {
		return conversorMedidasDao.findOne(id);
	}

	public List<ConversorMedidas> getAll() {
		return (List<ConversorMedidas>) conversorMedidasDao.findAll();
	}

	public void save(ConversorMedidas conversorMedidas) {
		conversorMedidasDao.save(conversorMedidas);
	}

	public void remover(Integer id) {
		conversorMedidasDao.delete(id);
	}
	
	public Double fatorConversao(Medida de, Medida para) {
		List<ConversorMedidas> todosConversorMedidas= (List<ConversorMedidas>) conversorMedidasDao.findAll();
		for (ConversorMedidas conversorMedidas : todosConversorMedidas)
		{
			if (conversorMedidas.getDe().getId() == de.getId() && conversorMedidas.getPara().getId() == para.getId())
			{
				return conversorMedidas.getTaxa();
			}
		}
		return 1D;
	}

}
