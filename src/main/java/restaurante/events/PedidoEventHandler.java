package restaurante.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import restaurante.model.Pedido;

@Component
@RepositoryEventHandler(Pedido.class)
public class PedidoEventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public PedidoEventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleBeforeCreate 
	public void newPedido(Pedido pedido) {
		System.out.println("NOVO PEDIDO!!!!!!!!!!!!");
		this.websocket.convertAndSend("/cozinha/update-fila/novo", getPath(pedido));
	}

	@HandleAfterSave
	public void updatePeedido(Pedido pedido) {
		this.websocket.convertAndSend("/cozinha/socket/updatePedido", getPath(pedido));
	}

	private String getPath(Pedido pedido) {
		return this.entityLinks.linkForSingleResource(pedido.getClass(), pedido.getId()).toUri().getPath();
	}

}
