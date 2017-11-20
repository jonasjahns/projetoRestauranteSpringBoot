package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Medida;

@RepositoryRestResource
public interface MedidaDao extends CrudRepository<Medida,Integer>{

}
