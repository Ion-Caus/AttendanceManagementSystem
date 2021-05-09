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

    ArrayList<Lesson> getScheduleFor(Student student, LocalDate date);

    Student getStudentBy(String id);
}
