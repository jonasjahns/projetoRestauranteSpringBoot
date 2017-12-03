package restaurante.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Comanda;
import restaurante.model.Usuario;

@RepositoryRestResource
public interface ComandaDao extends CrudRepository<Comanda, Integer> {

	@Query
	public List<Comanda> findByDataInicial(Date dataInicial);

	@Query
	public List<Comanda> findByUsuarioAndDataInicial(Usuario usuario, Date dataInicial);

	@Query
	public List<Comanda> findByUsuarioAndDataInicialBetween(Usuario usuario, Date dataInicialDe, Date dataInicialAte);

}
