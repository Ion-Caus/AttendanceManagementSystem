package model;


public class Class {
    private Schedule schedule;
    private StudentList students;
    private String className;

    public Class(String className) {
        this.className = className;
        this.schedule = new Schedule();
        this.students = new StudentList();
    }

    public String getClassName() {
        return className;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public StudentList getStudents() {
        return students;
    }
}
