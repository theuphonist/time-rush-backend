package app.timerush.api;

import java.time.Instant;

public class GameDTO {
    private String id;
    private String name;
    private int turnLength;
    private String turnLengthUnits;
    private String joinCode;
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

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
