package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Ingrediente;

@RepositoryRestResource
public interface IngredienteDao extends CrudRepository<Ingrediente, Integer> {

}
