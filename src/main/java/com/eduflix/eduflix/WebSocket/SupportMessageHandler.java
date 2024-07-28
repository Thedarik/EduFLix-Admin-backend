package com.eduflix.eduflix.WebSocket;

import com.eduflix.eduflix.Service.SupportMessageService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SupportMessageHandler extends TextWebSocketHandler {
    private final SupportMessageService supportMessageService;
    public SupportMessageHandler(SupportMessageService supportMessageService) {
        this.supportMessageService = supportMessageService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Register the session with the service
        supportMessageService.registerSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Unregister the session
        supportMessageService.unregisterSession(session);
    }
}
