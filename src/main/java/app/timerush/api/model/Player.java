package app.timerush.api.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("player")
public class Player {
    @Id
    private String id;
    private String name;
    private String color;
    private String gameId;
    private Integer position;
    private String sessionId;
    private Instant createdAt;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getGameId() {
        return this.gameId;
    }

    public Integer getPosition() {
        return this.position;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
