package restaurante.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/update-fila").withSockJS(); //aqui o JS vai se conectar
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/cozinha/socket"); // aqui ele vai se inscrever
		config.setApplicationDestinationPrefixes("/cozinha"); //prefixo de onde ele vai mandar as mensagens
	}

}
