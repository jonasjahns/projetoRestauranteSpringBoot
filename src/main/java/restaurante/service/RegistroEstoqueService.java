package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.RegistroEstoqueDao;
import restaurante.model.Ingrediente;
import restaurante.model.LancamentoEstoque;
import restaurante.model.Medida;
import restaurante.model.Pedido;
import restaurante.model.RegistroEstoque;
import restaurante.model.ValorPedido;

@Service
public class RegistroEstoqueService {
	@Autowired
	RegistroEstoqueDao registroEstoqueDao;

	@Autowired
	ConversorMedidasService conversorMedidasService;

	@Autowired
	TipoLancamentoEstoqueService tipoLancamentoEstoqueService;

	public RegistroEstoque getById(Integer id) {
		return registroEstoqueDao.findOne(id);
	}

	public List<RegistroEstoque> getAll() {
		return (List<RegistroEstoque>) registroEstoqueDao.findAll();
	}

	public void save(RegistroEstoque registroEstoque) {
		registroEstoqueDao.save(registroEstoque);
	}

	public void remover(Integer id) {
		registroEstoqueDao.delete(id);
	}

	public List<RegistroEstoque> registroPorIngrediente(Ingrediente ingrediente) {
		return registroEstoqueDao.findPorIngrediente(ingrediente.getId());
	}

	public void salvarReceita(Pedido pedido) {
		for (ValorPedido valorPedido : pedido.getValores()) {
			LancamentoEstoque lancamentoEstoque = new LancamentoEstoque();
			lancamentoEstoque.setData(pedido.getData());
			lancamentoEstoque.setValor(1D);
			lancamentoEstoque.setQuantidade((new Double(valorPedido.getValor())));
			lancamentoEstoque.setTipo(tipoLancamentoEstoqueService.getByCodigo(52));
			this.adicionarLancamento(lancamentoEstoque, valorPedido.getIngrediente(), valorPedido.getMedida());

		}
	}

	public void adicionarLancamento(LancamentoEstoque lancamentoEstoque, Ingrediente ingrediente, Medida medida) {
		List<RegistroEstoque> registrosEstoque = registroPorIngrediente(ingrediente);
		Double taxa = 1D;
		Double inseridos = 0D;
		for (RegistroEstoque registroEstoque : registrosEstoque) {
			taxa = conversorMedidasService.fatorConversao(medida, registroEstoque.getMedida());
			if (registroEstoque.getDisponiveis() >= lancamentoEstoque.getQuantidade() * taxa && inseridos == 0) {
				lancamentoEstoque.setQuantidade(lancamentoEstoque.getQuantidade() * taxa);
				registroEstoque.adicionarLancamento(lancamentoEstoque);
				inseridos = lancamentoEstoque.getQuantidade();
				registroEstoqueDao.save(registroEstoque);
			} else if (inseridos < lancamentoEstoque.getQuantidade()) {
				LancamentoEstoque novoLancamento = new LancamentoEstoque();
				novoLancamento.setData(lancamentoEstoque.getData());
				novoLancamento.setTipo(lancamentoEstoque.getTipo());
				novoLancamento.setValor(lancamentoEstoque.getValor());
				novoLancamento.setQuantidade(registroEstoque.getDisponiveis() * taxa - inseridos);
				registroEstoque.adicionarLancamento(novoLancamento);
				inseridos += novoLancamento.getQuantidade();
				registroEstoqueDao.save(registroEstoque);
			}
		}
	}
}
