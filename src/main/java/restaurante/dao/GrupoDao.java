package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Grupo;

@RepositoryRestResource
public interface GrupoDao extends CrudRepository<Grupo, Integer> {

}
