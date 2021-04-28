package model;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private LocalTime time;

    public Time(int hours, int min, int sec) throws DateTimeException {
        this.time = LocalTime.of(hours, min, sec);
    }

    public Time(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isBefore(Time time) {
        return this.time.isBefore(time.getTime());
    }

    public Time copy() {
        return new Time(time);
    }

    public String toString() {
        return DateTimeFormatter.ofPattern("HH:mm").toString();
    }


}
