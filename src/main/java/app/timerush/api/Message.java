package app.timerush.api;

public class Message {
    private String from;
    private String action;
    private Object data;

    public String getFrom() {
        return this.from;
    }

    public String getAction() {
        return this.action;
    }

    public Object getData() {
        return this.data;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
