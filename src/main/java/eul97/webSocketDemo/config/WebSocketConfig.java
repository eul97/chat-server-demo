package eul97.webSocketDemo.config;

import eul97.webSocketDemo.handler.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Client 에서 webSocket 연결할 때 사용할 API 경로를 설정
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 메시지를 보낼 경로 설정
        registry.enableSimpleBroker("/pub");

        // 메시지를 받을 경로 설정
        registry.setApplicationDestinationPrefixes("/sub");
    }
}
