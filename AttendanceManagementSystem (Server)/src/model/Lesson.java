package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Lesson
{
  private String topic;
  private Date date;
  private Time start, end;
  private final File[] resources;

  public Lesson(String topic, Date date, Time start, Time end, File[] res) {
    if (!hasValidTime(start, end))
      throw new IllegalArgumentException("Illegal lesson time");
    this.topic = topic;
    this.date = date.copy();
    this.start = start.copy();
    this.end = end.copy();
    this.resources = res;
  }

  public String getTopic()
  {
    return topic;
  }

  public Date getDate()
  {
    return date.copy();
  }

  public File[] getResources() {
    return this.resources;
  }

  public Time getDuration() {
    return start.timeTo(end);
  }

  public static boolean hasValidTime(Time startTime, Time endTime) {
    return !(startTime.isBefore(new Time(8, 20, 0))) //starting before 08:20
        && !(new Time(21, 15, 0).isBefore(endTime)) // ending after 21:15
        && !(endTime.isBefore(startTime)); // starting after ending
  }

  public void delayBy(int minutes) {
    if (minutes > 120)
      throw new IllegalArgumentException("Cannot be delayed by more than 120 minutes");

    Time newStartTime = this.start.copy();
    Time newEndTime = this.end.copy();
    newStartTime.tic(minutes * 60);
    newEndTime.tic(minutes * 60);

    if (!hasValidTime(newStartTime, newEndTime)) {
      throw new IllegalStateException("Delaying will result in invalid time");
    }
    this.start = newStartTime;
    this.start = newEndTime;
  }

  public String getDateTimeString() {
    return String.format("%s %s - %s", date, start, end);
  }

  @Override public boolean equals(Object obj) {
    if (!(obj instanceof Lesson))
      return false;
    Lesson temp = (Lesson) obj;
    if (this.resources.length != temp.resources.length)
      return false;
    for (int i = 0; i < resources.length; i++) {
      if (!this.resources[i].equals(temp.resources[i]))
        return false;
    }
    return Objects.equals(this.topic, temp.topic) && Objects.equals(this.date, temp.date);
  }

  @Override public String toString()
  {
    return "topic = " + topic + " Datetime " + getDateTimeString() + " Resources "
        + Arrays.toString(resources);
  }

  public String getTime() {
    return String.format("%s - %s", start, end);
  }
}
