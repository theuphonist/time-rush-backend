package app.timerush.api.model;

public class MessageData {
    private String playerId;
    private String timerValue;

    public String getPlayerId() {
        return playerId;
    }

    public String getTimerValue() {
        return timerValue;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setTimerValue(String timerValue) {
        this.timerValue = timerValue;
    }
}
