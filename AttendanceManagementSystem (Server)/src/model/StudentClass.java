package model;


import java.io.Serializable;

/**
 * A class representing a class
 */

public class StudentClass implements Serializable {
    private Schedule schedule;
    private StudentList students;
    private String className;

    /**
     * One parameter constructor that assigns a name to a class and initializes schedule and student list
     * @param className the name of the class
     */
    public StudentClass(String className) {
        this.className = className;
        this.schedule = new Schedule();
        this.students = new StudentList();
    }

    /**
     * @return returns the name of the class
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return return schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * @return returns the list of students
     */
    public StudentList getStudents() {
        return students;
    }
}
