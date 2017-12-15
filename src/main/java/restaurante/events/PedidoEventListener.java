package restaurante.events;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import restaurante.service.PedidoService;

@Component
public class PedidoEventListener {

	@Autowired
	private SimpMessagingTemplate websocket;

	@Autowired
	PedidoService pedidoService;

	@EventListener
	public void processPedidoEvent(PedidoEvent pedidoEvent) {
		System.out.println("\n ENTREI AQUIIII!!!!!!! \n");
		System.out.println(pedidoService.pedidosJsonByDate(new Date()));
		this.websocket.convertAndSend("/cozinha/socket", pedidoService.pedidosJsonByDate(new Date()));
	}

}
