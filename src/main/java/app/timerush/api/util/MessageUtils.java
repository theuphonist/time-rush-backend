package app.timerush.api.util;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import app.timerush.api.model.Message;

public class MessageUtils {
    private MessageUtils() {
    }

    public static void sendUpdatePlayerAndGameMessage(String gameId, SimpMessagingTemplate template) {
        final String destination = "/topic/" + gameId;
        final Message message = new Message();

        message.setAction(WebSocketAction.PLAYERS_OR_GAME_UPDATED);

        template.convertAndSend(destination, message);
    }
}
