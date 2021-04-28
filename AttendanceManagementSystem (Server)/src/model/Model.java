package model;

import java.util.ArrayList;

public interface Model {

    void setSchoolName(String name);

    ArrayList<Class> getAllClasses();

    ArrayList<Student> getAllStudents();

    ArrayList<Teacher> getAllTeachers();


}
