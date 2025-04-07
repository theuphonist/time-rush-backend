package app.timerush.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.lang.NonNull;

import app.timerush.api.model.Game;
import app.timerush.api.service.MessageService;

public class AfterGameSaveListener extends AbstractMongoEventListener<Game> {
    MessageService messageService;

    @Autowired
    public AfterGameSaveListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onAfterSave(@NonNull AfterSaveEvent<Game> event) {
        String gameId = event.getSource().getId();
        this.messageService.sendUpdateMessage(gameId);
    }

    @Override
    public void onBeforeDelete(@NonNull BeforeDeleteEvent<Game> event) {
        final String gameId = event.getSource().get("_id").toString();
        this.messageService.sendUpdateMessage(gameId);
    }
}
