package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private LocalDate date;

    public Date() {
        date = LocalDate.now();
    }

    public Date(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public Date(LocalDate date) {
        this.date = date;
    }

    public Date copy() {
        return new Date(this.date);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").toString();
    }

}
