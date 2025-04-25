package app.timerush.api.service;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import app.timerush.api.model.Message;
import app.timerush.api.util.WebSocketAction;

@Service
public class MessageService {
    private static final Integer SEND_UPDATE_MESSAGE_DELAY = 250;

    private final Set<String> gameIdsWithPendingUpdates;
    private final SimpMessagingTemplate template;

    @Autowired
    public MessageService(SimpMessagingTemplate template) {
        this.gameIdsWithPendingUpdates = new HashSet<>();
        this.template = template;
    }

    public void sendMessage(String topic, Message message) {
        if (message == null) {
            this.template.convertAndSend("/topic/" + topic, new Message());
            return;
        }

        this.template.convertAndSend("/topic/" + topic, message);
    }

    public void sendUpdateMessage(String gameId) {
        // debounce messages so they aren't getting spammed when there are a lot of
        // updates
        if (gameIdsWithPendingUpdates.contains(gameId)) {
            return;
        }

        final Message message = new Message();
        message.setAction(WebSocketAction.PLAYERS_OR_GAME_UPDATED);

        Timer timer = new Timer();

        TimerTask sendUpdateMessageTask = new TimerTask() {
            @Override
            public void run() {
                template.convertAndSend("/topic/" + gameId, message);
                gameIdsWithPendingUpdates.remove(gameId);
            }
        };

        timer.schedule(sendUpdateMessageTask, SEND_UPDATE_MESSAGE_DELAY);

        gameIdsWithPendingUpdates.add(gameId);
    }

}
