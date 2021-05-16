package model;

import java.util.ArrayList;

public class TeacherList {
    private ArrayList<Teacher> teachers;

    public TeacherList() {
        this.teachers = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) throws IllegalArgumentException {
        if (!isUnique(teacher.getID())) {
            throw new IllegalArgumentException("Teacher with ID ("+ teacher.getID() + ") already exists.");
        }
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void removeTeacher(String teacherID) {
        teachers.removeIf(teacher -> teacher.getID().equals(teacherID));
    }
    
    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }

    private boolean isUnique(String teacherID) {
        return teachers.stream().noneMatch(teacher -> teacher.getID().equals(teacherID));
    }

}


