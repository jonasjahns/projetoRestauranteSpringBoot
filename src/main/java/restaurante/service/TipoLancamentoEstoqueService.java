package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.TipoLancamentoEstoqueDao;
import restaurante.model.TipoLancamentoEstoque;

@Service
public class TipoLancamentoEstoqueService {
	@Autowired
	TipoLancamentoEstoqueDao tipoLancamentoEstoqueDao;

	public TipoLancamentoEstoque getById(Integer id) {
		return tipoLancamentoEstoqueDao.findOne(id);
	}

	public List<TipoLancamentoEstoque> getAll() {
		return (List<TipoLancamentoEstoque>) tipoLancamentoEstoqueDao.findAll();
	}

	public void save(TipoLancamentoEstoque tipoLancamentoEstoque) {
		tipoLancamentoEstoqueDao.save(tipoLancamentoEstoque);
	}

	public void remover(Integer id) {
		tipoLancamentoEstoqueDao.delete(id);
	}

	public TipoLancamentoEstoque getByCodigo(Integer codigo) {
		return tipoLancamentoEstoqueDao.findByCodigo(codigo);
	}
}
