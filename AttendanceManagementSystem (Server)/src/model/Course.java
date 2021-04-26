package model;

import java.util.ArrayList;

public class Course
{
  private String name;
  private ArrayList<Lesson> schedule;

  public Course(String name)
  {
    this.name = name;
    this.schedule = new ArrayList<>();
  }

  public String getName(){
    return this.name;
  }

  public int getNumberOfLessons(){
    return schedule.size();
  }

  public void addLesson(Lesson lesson){
    schedule.add(lesson);
  }

  public void removeLesson(Lesson lesson){
    schedule.remove(lesson);
  }

  public boolean hasLessonOnDate(Date date){
    return schedule.stream().anyMatch(lesson -> lesson.getDate().equals(date));
  }

  public ArrayList<Lesson> getAllLessons(){
    return this.schedule;
  }

  public ArrayList<String> getAllTopics(){
    ArrayList<String> topics = new ArrayList<>();
    schedule.stream().filter(lesson -> !topics.contains(lesson.getTopic()))
        .forEach(lesson -> topics.add(lesson.getTopic()));
    return topics;
  }


  public ArrayList<File> getAllResources(){
    ArrayList<File> allResources = new ArrayList<>();

    for(Lesson lesson: schedule){
      for(File file: lesson.getResources()){
        allResources.add(file);
      }
    }
    return allResources;
  }


  @Override
  public String toString(){
    return this.name + schedule;
  }
}
