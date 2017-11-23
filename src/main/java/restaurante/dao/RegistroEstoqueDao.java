package restaurante.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.RegistroEstoque;

@RepositoryRestResource
public interface RegistroEstoqueDao extends CrudRepository<RegistroEstoque, Integer> {

	@Query (value="select * from REGISTROS_ESTOQUES t where t.ingrediente_id= ?1 order by validade asc", nativeQuery = true)
	public List<RegistroEstoque> findPorIngrediente(Integer ingredienteId);

}
