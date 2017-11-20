package restaurante.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.TipoLancamentoEstoque;

@RepositoryRestResource
public interface TipoLancamentoEstoqueDao extends CrudRepository<TipoLancamentoEstoque, Integer> {
	@Query
	public TipoLancamentoEstoque findByCodigo(Integer codigo);
}
