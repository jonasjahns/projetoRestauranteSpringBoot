package restaurante.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.UsuarioProfile;

@RepositoryRestResource
public interface UsuarioProfileDao extends CrudRepository<UsuarioProfile, Integer> {
	@Query
	public UsuarioProfile findByTipo(String tipo);
}
