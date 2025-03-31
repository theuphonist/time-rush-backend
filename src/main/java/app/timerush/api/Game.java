package app.timerush.api;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("game")
public class Game {
    @Id
    private String id;
    private String name;
    private int turnLength;
    private String turnLengthUnits;
    private String joinCode;
    private String status;
    private String hostPlayerId;
    private Instant createdAt;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getTurnLength() {
        return this.turnLength;
    }

    public String getTurnLengthUnits() {
        return this.turnLengthUnits;
    }

    public String getJoinCode() {
        return this.joinCode;
    }

    public String getStatus() {
        return status;
    }

    public String getHostPlayerId() {
        return hostPlayerId;
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

    public void setTurnLength(int turnLength) {
        this.turnLength = turnLength;
    }

    public void setTurnLengthUnits(String turnLengthUnits) {
        this.turnLengthUnits = turnLengthUnits;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHostPlayerId(String hostPlayerId) {
        this.hostPlayerId = hostPlayerId;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
