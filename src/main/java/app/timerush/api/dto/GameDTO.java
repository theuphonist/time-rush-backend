package app.timerush.api.dto;

import java.time.Instant;

public class GameDTO {
    private String id;
    private String name;
    private Integer turnLength;
    private String joinCode;
    private String status;
    private Boolean paused;
    private String hostPlayerId;
    private String activePlayerId;
    private Instant createdAt;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getTurnLength() {
        return this.turnLength;
    }

    public String getJoinCode() {
        return this.joinCode;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getPaused() {
        return paused;
    }

    public String getHostPlayerId() {
        return hostPlayerId;
    }

    public String getActivePlayerId() {
        return activePlayerId;
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

    public void setTurnLength(Integer turnLength) {
        this.turnLength = turnLength;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public void setHostPlayerId(String hostPlayerId) {
        this.hostPlayerId = hostPlayerId;
    }

    public void setActivePlayerId(String activePlayerId) {
        this.activePlayerId = activePlayerId;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
