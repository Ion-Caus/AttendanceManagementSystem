package model;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {

    void setSchoolName(String name);

    ArrayList<Class> getAllClasses();

    ArrayList<Student> getAllStudents();

    ArrayList<Teacher> getAllTeachers();

    ArrayList<Administrator> getAllAdministrators();

    void removeClass(String classname);

    void removeStudent(String studentName);

    void addStudent(String student, String id);

    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date);

    Student getStudentBy(String id);
}
