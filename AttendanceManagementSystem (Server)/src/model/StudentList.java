package model;

import java.util.ArrayList;

public class StudentList
{
  private ArrayList<Student> students;

  public StudentList() {
    this.students = new ArrayList<>();
  }

  public void addStudent(Student student){
    students.add(student);
  }

  public void removeStudent(Student student){
    students.remove(student);
  }

  public ArrayList<Student> getAllStudents() {
    return students;
  }

}
