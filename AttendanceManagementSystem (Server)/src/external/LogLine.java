package external;

public class LogLine {
    private DateTime dateTime;
    private String text;

    public LogLine(String text) {
        this.text = text;
        this.dateTime = new DateTime();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", dateTime, text);
    }
}
