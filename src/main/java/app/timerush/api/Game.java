package app.timerush.api;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("game")
public class Game {
    @Id
    private String id;
    private String name;
    private int turnLength;
    private String turnLengthUnits;
    private List<String> playerIds;
    private String hostPlayerId;
    private String joinCode;

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

    public List<String> getPlayerIds() {
        return this.playerIds;
    }

    public String getHostPlayerId() {
        return this.hostPlayerId;
    }

    public String getJoinCode() {
        return this.joinCode;
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

    public void setPlayerIds(List<String> playerIds) {
        this.playerIds = playerIds;
    }

    public void setHostPlayerId(String hostPlayerId) {
        this.hostPlayerId = hostPlayerId;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
