package restaurante.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import restaurante.model.Pedido;
import restaurante.model.Usuario;

@RepositoryRestResource(collectionResourceRel = "pedidos", path = "/pedido")
public interface PedidoDao extends CrudRepository<Pedido, Integer> {

	@Query
	public List<Pedido> findByStatus(Integer status);

	@Query
	public List<Pedido> findByData(Date data);

	@Query
	public List<Pedido> findByUsuarioAndData(Usuario usuario, Date data);

	@Query
	public List<Pedido> findByUsuarioAndStatus(Usuario usuario, Integer status);

	@Query
	public List<Pedido> findByDataAndStatus(Date data, Integer status);

	@Query
	public List<Pedido> findByUsuarioAndDataBetween(Usuario usuario, Date dataDe, Date dataAte);
}
