package app.timerush.time_rush_backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("game")
public class Game {
    @Id
    private String id;

    private String name;
    private short turnLength;
    private String turnLengthUnits;
    private String joinCode;

    public Game(String id, String name, short turnLength, String turnLengthUnits, String joinCode) {
        super();
        this.id = id;
        this.name = name;
        this.turnLength = turnLength;
        this.turnLengthUnits = turnLengthUnits;
        this.joinCode = joinCode;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public short getTurnLength() {
        return this.turnLength;
    }

    public String getTurnLengthUnits() {
        return this.turnLengthUnits;
    }

    public String getJoinCode() {
        return this.joinCode;
    }
}
