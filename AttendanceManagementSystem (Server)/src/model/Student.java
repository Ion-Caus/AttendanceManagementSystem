package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Student
{
  private int studentNumber;
  private String name;
  private ArrayList<Course> courses;
  private ArrayList<Grade> grades;

  public Student(int studentNumber, String name)
  {
    this.studentNumber = studentNumber;
    this.name = name;
    this.courses = new ArrayList<>();
    this.grades = new ArrayList<>();
  }

  public int getStudentNumber()
  {
    return studentNumber;
  }

  public String getName()
  {
    return name;
  }

  public void addCourse(Course course){
    courses.add(course);
  }

  public void addGrade(int grade, Course course) throws IllegalArgumentException {
    if (!courses.contains(course))
      throw new IllegalArgumentException("The student's course list does not contain this course");
    grades.add(new Grade(grade,course));
  }

  public double getGradeAverage(){
    double sum = 0;
    double count = 0;
    for(Grade grade: grades){
      sum+=grade.getGrade();
      count++;
    }
    return sum/count;
  }

  public ArrayList<Grade> getAllGrades(){
    return grades;
  }

  public ArrayList<Course> getAllCourses(){
    return courses;
  }

//  public Grade[] getAllGrades(){
//    Grade[] allGrades = new Grade[this.grades.size()];
//    Arrays.setAll(allGrades, i -> this.grades.get(i).copy());
//    return allGrades;
//  }
//
//  public Course[] getAllCourses(){
//    Course[] allCourses = new Course[this.courses.size()];
//    Arrays.setAll(allCourses, i -> this.courses.get(i));
//    return allCourses;
//  }


}
