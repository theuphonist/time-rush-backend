package app.timerush.api;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import app.timerush.api.PlayerController.WebSocketAction;

public class MessageUtils {
    private MessageUtils() {
    }

    public static void sendUpdatePlayerMessage(String gameId, SimpMessagingTemplate template) {
        final String destination = "/topic/game/" + gameId;
        final Message message = new Message();

        message.setAction(WebSocketAction.UPDATE_PLAYER.get());

        template.convertAndSend(destination, message);
    }
}
