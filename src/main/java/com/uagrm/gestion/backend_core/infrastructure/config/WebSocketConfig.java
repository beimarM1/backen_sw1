package com.uagrm.gestion.backend_core.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuración de WebSockets para soporte de edición colaborativa en tiempo
 * real.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilitar un broker simple en memoria
        // /topic: para mensajes broadcast (uno a muchos)
        // /queue: para mensajes punto a punto (uno a uno)
        config.enableSimpleBroker("/topic", "/queue");

        // Prefijo para los mensajes que van desde el cliente al servidor
        // (@MessageMapping)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-workflow")
                // Aquí también debes ser específico con la URL de Netlify
                .setAllowedOriginPatterns("https://enterprise-diagrammer.netlify.app", "http://localhost:4200")
                .withSockJS();
    }
}
