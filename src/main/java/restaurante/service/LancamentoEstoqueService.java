package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.LancamentoEstoqueDao;
import restaurante.model.LancamentoEstoque;

@Service
public class LancamentoEstoqueService {
	@Autowired
	LancamentoEstoqueDao lancamentoEstoqueDao;

	public LancamentoEstoque getById(Integer id) {
		return lancamentoEstoqueDao.findOne(id);
	}

	public List<LancamentoEstoque> getAll() {
		return (List<LancamentoEstoque>) lancamentoEstoqueDao.findAll();
	}

	public void save(LancamentoEstoque lancamentoEstoque) {
		lancamentoEstoqueDao.save(lancamentoEstoque);
	}

	public void remover(Integer id) {
		lancamentoEstoqueDao.delete(id);
	}
}
