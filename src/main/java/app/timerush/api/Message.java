package app.timerush.api;

public class Message {
    private String action;
    private MessageData data;

    public String getAction() {
        return this.action;
    }

    public MessageData getData() {
        return this.data;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(MessageData data) {
        this.data = data;
    }
}
