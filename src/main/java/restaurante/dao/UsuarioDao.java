package restaurante.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Usuario;

@RepositoryRestResource
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

	@Query
	public Usuario findByCpf(String CPF);
	
	@Query
	public void deleteByCpf(String CPF);
}
