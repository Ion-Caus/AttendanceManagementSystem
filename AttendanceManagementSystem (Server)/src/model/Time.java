package model;

public class Time
{
  private int hour, minute, second;

  public Time(int h, int m, int s) throws IllegalArgumentException {
    if (h > 23 || m > 59 || s > 59)
      throw new IllegalArgumentException("Invalid time format");
    else {
      this.hour = h;
      this.minute = m;
      this.second = s;
    }
  }

  public Time(int totalTimeInSeconds) throws IllegalArgumentException {

    if (totalTimeInSeconds >= 86400)
      throw new IllegalArgumentException("Time cannot exceed 24 hours");

    this.hour = totalTimeInSeconds / 3600; //integer division
    totalTimeInSeconds -= this.hour * 3600; //subtract hours
    this.minute = totalTimeInSeconds / 60; //integer division
    totalTimeInSeconds -= this.minute * 60; //subtract minutes
    this.second = totalTimeInSeconds;
  }

  public void tic() {
    if (this.second < 59) {
      this.second += 1;
    }
    else {
      this.second = 0;
      if (this.minute >= 59) {
        this.minute = 0;
        this.hour += 1;
        if (this.hour >= 24)
          this.hour = 0;
      }
      else {
        this.minute += 1;
      }
    }
  }

  public void tic(int seconds) {
    for (int i = 1; i <= seconds; i++) {
      tic();
    }
  }

  public int convertToSeconds() {
    return this.hour * 3600 + this.minute * 60 + this.second;
  }

  public boolean isBefore(Time time2) {
    return convertToSeconds() < time2.convertToSeconds();
  }

  public Time timeTo(Time time2) {
    if (isBefore(time2)) {
      int inSeconds = time2.convertToSeconds() - convertToSeconds();
      return new Time(inSeconds);
    }
    else {
      int inSeconds = 86400 - (convertToSeconds() - time2.convertToSeconds());
      return new Time(inSeconds);
    }
  }

  public Time copy() {
    return new Time(this.hour, this.minute, this.second);
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof Time) || obj == null)
      return false;
    Time temp = (Time) obj;
    return this.hour == temp.hour && this.minute == temp.minute
        && this.second == temp.second;
  }

  @Override public String toString() {
    String temp = "";
    if (this.hour <= 9) {
      temp += "0" + this.hour;
    }
    else
      temp += this.hour;

    if (this.minute <= 9) {
      temp += ":0" + this.minute;
    }
    else
      temp += ":" + this.minute;

    if (this.second <= 9) {
      temp += ":0" + this.second;
    }
    else
      temp += ":" + this.second;

    return temp;
  }
}

