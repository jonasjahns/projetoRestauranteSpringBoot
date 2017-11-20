package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.LancamentoEstoque;

@RepositoryRestResource
public interface LancamentoEstoqueDao extends CrudRepository<LancamentoEstoque, Integer> {

}
