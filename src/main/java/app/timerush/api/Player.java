package app.timerush.api;

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
    private boolean isHost;
    private Integer position;
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

    public boolean getIsHost() {
        return this.isHost;
    }

    public Integer getPosition() {
        return this.position;
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

    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
