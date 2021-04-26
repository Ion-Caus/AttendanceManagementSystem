package model;

public class LessonData
{
  private Lesson lesson;
  private Grade grade;
  private Absence absence;

  public LessonData(Lesson lesson, Grade grade, Absence absence) {
    this.lesson = lesson;
    this.grade = grade.copy();
    this.absence = absence.copy();
  }

  public Grade getGrade() {
    return grade;
  }

  public Absence getAbsence() {
    return absence;
  }

  public Lesson getLesson() {
    return lesson;
  }


}
