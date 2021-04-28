package model;

import java.util.ArrayList;

public class Schedule
{
  private ArrayList<Lesson> schedule;

  public Schedule() {
    this.schedule = new ArrayList<>();
  }

  public void addLesson(Lesson lesson){
    schedule.add(lesson);
  }

  public void removeLesson(Lesson lesson){
    schedule.remove(lesson);
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
