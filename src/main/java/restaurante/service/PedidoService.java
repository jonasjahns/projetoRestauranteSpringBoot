package restaurante.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import restaurante.dao.PedidoDao;
import restaurante.model.Pedido;
import restaurante.model.Usuario;

@Service
public class PedidoService {
	@Autowired
	PedidoDao pedidoDao;

	public Pedido getById(Integer id) {
		return pedidoDao.findOne(id);
	}

	public List<Pedido> getAll() {
		return (List<Pedido>) pedidoDao.findAll();
	}

	public void save(Pedido pedido) {
		pedidoDao.save(pedido);
	}

	public void remover(Integer id) {
		pedidoDao.delete(id);
	}

	public List<Pedido> getByStatus(Integer status) {
		return pedidoDao.findByStatus(status);
	}

	public List<Pedido> findByData(Date data) {
		return pedidoDao.findByData(data);
	}

	public List<Pedido> findByUsuarioAndData(Usuario usuario, Date data) {
		return pedidoDao.findByUsuarioAndData(usuario, data);
	}

	public List<Pedido> findByUsuarioAndStatus(Usuario usuario, Integer status) {
		return pedidoDao.findByUsuarioAndStatus(usuario, status);
	}

	public List<Pedido> findByDataAndStatus(Date data, Integer status) {
		return pedidoDao.findByDataAndStatus(data, status);
	}

	@Cacheable("pedidos")
	public List<Pedido> findByUsuarioAndDataAfterAndDataBefore(Usuario usuario, Date dataDe, Date dataAte) {
		return pedidoDao.findByUsuarioAndDataBetween(usuario, dataDe, dataAte);
	}

	public List<Pedido> findByDataBetween(Date dataDe, Date dataAte) {
		return pedidoDao.findByDataBetween(dataDe, dataAte);
	}
}
