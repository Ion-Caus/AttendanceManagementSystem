package model;

public class Date
{
  private int day, month, year;

  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public Date copy(){
    return new Date(this.day,this.month,this.year);
  }

  @Override public boolean equals(Object obj)
  {
    if (!(obj instanceof Date))
      return false;
    Date date = (Date) obj;
    return day == date.day && month == date.month && year == date.year;
  }

  @Override public String toString()
  {
    return String.format("%s/%s/%s",day,month,year);
  }
}
