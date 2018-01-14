package restaurante.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import restaurante.dao.PedidoDao;
import restaurante.events.PedidoEvent;
import restaurante.model.Pedido;
import restaurante.model.RegistroFilaCozinha;
import restaurante.model.Usuario;

@Service
public class PedidoService {
	@Autowired
	PedidoDao pedidoDao;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	RegistroEstoqueService registroEstoqueService;

	public Pedido getById(Integer id) {
		return pedidoDao.findOne(id);
	}

	public List<Pedido> getAll() {
		return (List<Pedido>) pedidoDao.findAll();
	}

	public void save(Pedido pedido) {
		pedidoDao.save(pedido);
		final PedidoEvent event = new PedidoEvent(pedido);
		applicationEventPublisher.publishEvent(event);
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
	
	public void salvarPedidoAndroid(Pedido pedido)
	{
		pedidoDao.save(pedido);
		registroEstoqueService.salvarReceita(pedido);
	}

	public String pedidosJsonByDate(Date data) {
		//String separator = "\"";
				//String json = "[";//"{" + separator + "data" + separator + ":[";
				//String comma = ",";
				//int i = 1;
				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				List<Pedido> pedidos = this.findByData(data);
				List<RegistroFilaCozinha> registros = new ArrayList<RegistroFilaCozinha>();
				for (Pedido pedido : pedidos) {
					//json +="[";
					//json += separator + pedido.getReceita().getNome() + separator + comma;
					//json += separator + pedido.getUsuario().getNomeCompleto() + separator + comma;
					//json += separator + pedido.getNomeStatus() + separator + comma;
					//json += separator + pedido.getDataFormatada() + separator + comma;
					//json += separator + pedido.getUltimoStatus().getHora() + separator + comma;
					//json += separator + "Botao" + separator;
					//json += "]";
					//if (i < pedidos.size())
					//{
						//json += comma;
					//}
					//i++;
					RegistroFilaCozinha registro = new RegistroFilaCozinha();
					registro.setPrato(pedido.getReceita().getNome());
					registro.setCliente(pedido.getUsuario().getNomeCompleto());
					registro.setStatus(pedido.getNomeStatus());
					registro.setHoraPedido(pedido.getDataFormatada());
					registro.setUltimaAtualizacao(df.format(pedido.getUltimoStatus().getHora()));
					registros.add(registro);
				}
				//json += "]}";
				//json +="]";
				Gson g = new Gson();
				return g.toJson(registros);
	}
}
