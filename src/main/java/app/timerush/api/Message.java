package app.timerush.api;

public class Message {
    private String content;
    private String from;

    public Message() {
        this.content = "";
        this.from = "";
    }

    public String getContent() {
        return this.content;
    }

    public String getFrom() {
        return this.from;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
