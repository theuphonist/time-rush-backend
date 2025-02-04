package app.timerush.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
public class MessageController implements ApplicationListener<SessionDisconnectEvent> {

    private final SimpMessagingTemplate template;
    private final WebSocketSessionRepository webSocketSessionRepo;
    private final PlayerRepository playerRepo;

    @Autowired
    public MessageController(SimpMessagingTemplate template, WebSocketSessionRepository webSocketSessionRepo,
            PlayerRepository playerRepo) {
        this.template = template;
        this.webSocketSessionRepo = webSocketSessionRepo;
        this.playerRepo = playerRepo;
    }

    @Override
    public void onApplicationEvent(@NonNull SessionDisconnectEvent event) {
        final String sessionId = event.getSessionId();

        this.markAssociatedPlayerAsDisconnected(sessionId);
        this.webSocketSessionRepo.deleteById(sessionId);
    }

    @MessageMapping("/game/{gameId}")
    public void relayToGameSubscribers(@DestinationVariable String gameId, Message message) throws Exception {
        this.template.convertAndSend("/topic/game/" + gameId, message);
    }

    @MessageMapping("/map-session")
    public void mapPlayerIdToSessionId(@Payload Message message, SimpMessageHeaderAccessor headerAccessor)
            throws Exception {
        WebSocketSession webSocketSession = new WebSocketSession();
        final String playerId = message.getData().getPlayerId();

        webSocketSession.setId(headerAccessor.getSessionId());
        webSocketSession.setPlayerId(message.getData().getPlayerId());

        this.webSocketSessionRepo.save(webSocketSession);

        Optional<Player> optionalPlayer = this.playerRepo.findById(playerId);

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setIsConnected(true);
            this.playerRepo.save(player);

            MessageUtils.sendUpdatePlayerMessage(player.getGameId(), this.template);
        }
    }

    @MessageMapping("/delete-session")
    public void deleteSession(SimpMessageHeaderAccessor headerAccessor)
            throws Exception {
        final String sessionId = headerAccessor.getSessionId();
        if (sessionId != null) {
            this.markAssociatedPlayerAsDisconnected(sessionId);
            this.webSocketSessionRepo.deleteById(sessionId);
        }

        this.markAssociatedPlayerAsDisconnected(sessionId);
    }

    private void markAssociatedPlayerAsDisconnected(String sessionId) {
        Optional<WebSocketSession> optionalSession = this.webSocketSessionRepo.findById(sessionId);

        if (optionalSession.isPresent()) {

            String playerId = optionalSession.get().getPlayerId();

            Optional<Player> optionalPlayer = this.playerRepo.findById(playerId);

            if (optionalPlayer.isPresent()) {
                Player player = optionalPlayer.get();
                player.setIsConnected(false);
                this.playerRepo.save(player);
                MessageUtils.sendUpdatePlayerMessage(player.getGameId(), this.template);
            }
        }
    }
}
