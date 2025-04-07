package app.timerush.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.lang.NonNull;

import app.timerush.api.model.Player;
import app.timerush.api.service.MessageService;
import app.timerush.api.service.PlayerService;

public class AfterPlayerSaveListener extends AbstractMongoEventListener<Player> {
    MessageService messageService;
    PlayerService playerService;

    @Autowired
    public AfterPlayerSaveListener(MessageService messageService, PlayerService playerService) {
        this.messageService = messageService;
        this.playerService = playerService;
    }

    @Override
    public void onAfterSave(@NonNull AfterSaveEvent<Player> event) {
        String gameId = event.getSource().getGameId();
        this.messageService.sendUpdateMessage(gameId);
    }

    @Override
    public void onBeforeDelete(@NonNull BeforeDeleteEvent<Player> event) {
        final String playerId = event.getSource().get("_id").toString();
        final String gameId = this.playerService.getPlayerById(playerId).getGameId();
        this.messageService.sendUpdateMessage(gameId);
    }
}
