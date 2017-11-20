package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.controller.ReceitaGrupo;

@RepositoryRestResource
public interface ReceitaGrupoDao extends CrudRepository<ReceitaGrupo, Integer> {

}
