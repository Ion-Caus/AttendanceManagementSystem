package model;

import java.util.ArrayList;

public class TeacherList {
    private ArrayList<Teacher> teachers;

    public TeacherList() {
        this.teachers = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }

}


