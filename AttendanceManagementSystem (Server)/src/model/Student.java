package model;

public class Student
{
  private LessonData lessonData;
  private String name;
  private String ID;

  private Account account;

  public Student(String name,  String ID, LessonData lessonData) {
    this.name = name;
    this.ID = ID;
    this.lessonData = lessonData;
    this.account = null;
  }

  public void setAccount(Account account){
    this.account = account;
  }

  public LessonData getLessonData() {
    return lessonData;
  }

  public String getName() {
    return name;
  }

  public String getID() {
    return ID;
  }
}
