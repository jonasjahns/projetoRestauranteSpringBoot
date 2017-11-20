package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Receita;

@RepositoryRestResource
public interface ReceitaDao extends CrudRepository<Receita, Integer> {

}
