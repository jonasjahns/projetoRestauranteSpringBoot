package restaurante.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.RegistroEstoque;

@RepositoryRestResource
public interface RegistroEstoqueDao extends CrudRepository<RegistroEstoque, Integer> {

	@Query
	public List<RegistroEstoque> findByOrderByValidadeAscIngrediente_Id(Integer ingredienteId);

}
