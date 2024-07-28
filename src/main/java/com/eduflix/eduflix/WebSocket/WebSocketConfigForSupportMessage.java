package com.eduflix.eduflix.WebSocket;

import com.eduflix.eduflix.Service.SupportMessageService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfigForSupportMessage implements WebSocketConfigurer {
    private final SupportMessageService supportMessageService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SupportMessageHandler(supportMessageService), "/ws/chat")
                .setAllowedOrigins("*");
    }
}