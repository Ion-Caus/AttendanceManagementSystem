package external;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private LocalDateTime time;

    public DateTime() {
        this.time = LocalDateTime.now();
    }

    public DateTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTimestamp() {
        DateTimeFormatter dtf;
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return time.format(dtf);
    }

    public String getSortableDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(dtf);
    }

    @Override
    public String toString() {
        return getTimestamp();
    }
}