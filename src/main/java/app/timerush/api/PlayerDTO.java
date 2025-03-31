package app.timerush.api;

import java.time.Instant;

public class PlayerDTO {
    private String id;
    private String name;
    private String color;
    private String gameId;
    private Integer position;
    private Boolean isConnected;
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

    public Boolean getIsConnected() {
        return this.isConnected;
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

    public void setIsConnected(Boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
