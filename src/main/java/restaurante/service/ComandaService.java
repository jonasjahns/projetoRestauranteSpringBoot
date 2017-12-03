package restaurante.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.ComandaDao;
import restaurante.model.Comanda;
import restaurante.model.Usuario;

@Service
public class ComandaService {

	@Autowired
	ComandaDao comandaDao;

	public Comanda getById(Integer id) {
		return comandaDao.findOne(id);
	}

	public List<Comanda> getAll() {
		return (List<Comanda>) comandaDao.findAll();
	}

	public void save(Comanda comanda) {
		comandaDao.save(comanda);
	}

	public void remover(Integer id) {
		comandaDao.delete(id);
	}

	public List<Comanda> findByDataInicial(Date dataInicial) {
		return comandaDao.findByDataInicial(dataInicial);
	}

	public List<Comanda> findByUsuarioAndDataInicial(Usuario usuario, Date dataInicial) {
		return comandaDao.findByUsuarioAndDataInicial(usuario, dataInicial);
	}

	public List<Comanda> findByUsuarioAndDataInicialBetween(Usuario usuario, Date dataInicialDe, Date dataInicialAte) {
		return comandaDao.findByUsuarioAndDataInicialBetween(usuario, dataInicialDe, dataInicialAte);
	}

}
