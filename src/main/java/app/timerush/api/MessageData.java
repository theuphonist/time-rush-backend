package app.timerush.api;

public class MessageData {
    private String playerId;
    private String activePlayerId;

    public String getPlayerId() {
        return playerId;
    }

    public String getActivePlayerId() {
        return activePlayerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void getActivePlayerId(String activePlayerId) {
        this.activePlayerId = activePlayerId;
    }
}
