package model;

import java.util.ArrayList;

public class Class
{
  private ArrayList<Lesson> schedule;
  private ArrayList<Student> students;
  private String className;

  public Class(String className) {
    this.className = className;
    this.schedule = new ArrayList<>();
    this.students = new ArrayList<>();
  }

  public void addLesson(Lesson lesson){
    schedule.add(lesson);
  }

  public void addStudent(Student student){
    students.add(student);
  }

  public void removeLesson(Lesson lesson){
    schedule.remove(lesson);
  }

  public void removeStudent(Student student){
    students.remove(student);
  }

  public ArrayList<Lesson> getLessonBy(Date date){
    ArrayList<Lesson> lessons = new ArrayList<>();
    for(Lesson lesson:schedule){
      if(lesson.getLessonDate().equals(date))
        lessons.add(lesson);
    }
    return lessons;
  }

  public ArrayList<Lesson> getLessonBy(Teacher teacher){
    ArrayList<Lesson> lessons = new ArrayList<>();
    for(Lesson lesson:schedule){
      if(lesson.getTeacher().equals(teacher))
        lessons.add(lesson);
    }
    return lessons;
  }

  public ArrayList<Lesson> getAllLessons(){
    return schedule;
  }


}
