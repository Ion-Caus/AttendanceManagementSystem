package model;


public class Lesson
{
  private Teacher teacher;
  private Date lessonDate;
  private Time startTime, endTime;
  private String subject;
  private String topic;
  private String classroom;
  private String homework;

  public Lesson(Teacher teacher, Date lessonDate, Time startTime, Time endTime,
      String subject, String topic, String classroom, String homework)
  {
    this.teacher = teacher;
    this.lessonDate = lessonDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.subject = subject;
    this.topic = topic;
    this.classroom = classroom;
    this.homework = homework;
  }

  public static boolean hasValidTime(Time startTime, Time endTime) {
    return !(endTime.isBefore(startTime));
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public Date getLessonDate() {
    return lessonDate;
  }

  public String getSubject() {
    return subject;
  }

  public String getTopic() {
    return topic;
  }

  public String getClassroom() {
    return classroom;
  }

  public String getHomework() {
    return homework;
  }

  public String getTimeInterval(){
    return String.format("%s - %s",startTime,endTime);
  }

}
