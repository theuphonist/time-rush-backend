package app.timerush.api.model;

public class Message {
    private String action;
    private MessageData data;

    public String getAction() {
        return action;
    }

    public MessageData getData() {
        return data;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(MessageData data) {
        this.data = data;
    }
}
