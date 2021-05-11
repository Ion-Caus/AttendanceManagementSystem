package model;

import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeStudent(String studentID) {
        students.removeIf(student -> student.getID().equals(studentID));
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student getStudentByID(String id) throws IllegalArgumentException {
        for (Student student: students) {
            if (student.getID().equals(id)) {
                return student;
            }
        }
        throw new IllegalArgumentException("No such student with this id (" + id + ")");
    }

    public Student getStudentByName(String name) throws IllegalArgumentException {
        for (Student student: students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new IllegalArgumentException("No such student with this name (" + name + ")");
    }

}
