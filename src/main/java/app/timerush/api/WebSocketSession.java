package app.timerush.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("webSocketSession")
public class WebSocketSession {
    @Id
    private String id;
    private String playerId;

    public String getId() {
        return this.id;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
