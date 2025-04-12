package app.timerush.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import app.timerush.api.model.Message;
import app.timerush.api.util.WebSocketAction;

@Service
public class MessageService {
    private final SimpMessagingTemplate template;

    @Autowired
    public MessageService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void relayToGameSubscribers(String gameId, Message message) {
        this.template.convertAndSend("/topic/" + gameId, message);
    }

    public void sendUpdateMessage(String gameId) {
        final Message message = new Message();
        message.setAction(WebSocketAction.PLAYERS_OR_GAME_UPDATED);

        template.convertAndSend("/topic/" + gameId, message);
    }

}
