package restaurante.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.ConversorMedidas;

@RepositoryRestResource
public interface ConversorMedidasDao extends CrudRepository<ConversorMedidas, Integer> {

}
