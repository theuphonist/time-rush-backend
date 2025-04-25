package app.timerush.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import app.timerush.api.model.Message;
import app.timerush.api.service.MessageService;
import app.timerush.api.service.PlayerService;

@Controller
public class MessageController implements ApplicationListener<SessionDisconnectEvent> {

    private final PlayerService playerService;
    private final MessageService messageService;

    @Autowired
    public MessageController(PlayerService playerService, MessageService messageService) {
        this.playerService = playerService;
        this.messageService = messageService;
    }

    @Override
    public void onApplicationEvent(@NonNull SessionDisconnectEvent event) {
        final String sessionId = event.getSessionId();
        this.playerService.disconnectPlayerBySessionId(sessionId);
    }

    @MessageMapping("/{gameId}")
    public void relayToGameSubscribers(@DestinationVariable String gameId, Message message) throws Exception {
        this.messageService.sendMessage(gameId, message);
    }

    @MessageMapping("/connect/{playerId}")
    public void connectPlayer(@DestinationVariable String playerId, SimpMessageHeaderAccessor headerAccessor) {
        this.messageService.sendMessage(String.format("connect/%s", playerId), null);
        final String sessionId = headerAccessor.getSessionId();
        this.playerService.connectPlayer(playerId, sessionId);
    }

    @MessageMapping("/disconnect")
    public void disconnectPlayerBySessionId(SimpMessageHeaderAccessor headerAccessor) {
        final String sessionId = headerAccessor.getSessionId();

        this.playerService.disconnectPlayerBySessionId(sessionId);
    }
}
