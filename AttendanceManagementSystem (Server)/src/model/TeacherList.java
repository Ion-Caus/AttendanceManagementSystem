package model;

import java.util.ArrayList;

/**
 * This class serves as a containing element for class Teacher
 */
public class TeacherList {
    private ArrayList<Teacher> teachers;

    /**
     * 0 argument constructor that will initialize teachers ArrayList to new ArrayList
     */
    public TeacherList() {
        this.teachers = new ArrayList<>();
    }

    /** 1 argument constructor that will initialize teachers ArrayList using the ArrayList provided as an argument
     * @param teachers
     */
    public TeacherList(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    /** This method will add teacher to the teachers ArrayList
     * @param teacher the teacher object provided as an argument
     * @throws IllegalArgumentException will be thrown in case the teacher alredy exists
     */
    public void addTeacher(Teacher teacher) throws IllegalArgumentException {
        if (!isUnique(teacher.getID())) {
            throw new IllegalArgumentException("Teacher with ID ("+ teacher.getID() + ") already exists.");
        }
        teachers.add(teacher);
    }


    /**
     * Will remove the teacher with matching id
     * @param teacherID the teacher id provided as an argument
     */

    public void removeTeacher(String teacherID) {
        teachers.removeIf(teacher -> teacher.getID().equals(teacherID));
    }
    
    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }

    /**
     * @param teacherID the id of the teacher that is provided as an argument
     * @return true if there is no teacher with this id, false if there is a teacher matching the provided id
     */
    private boolean isUnique(String teacherID) {
        return teachers.stream().noneMatch(teacher -> teacher.getID().equals(teacherID));
    }

    /**
     * @param id the Teacher's id provided as an argument
     * @return Teacher object that has matching id with the id provided as an argument
     * @throws IllegalArgumentException will be thrown in case there is no teacher with matching id
     */
    public Teacher getTeacherByID(String id) throws IllegalArgumentException {
        for (Teacher teacher: teachers) {
            if (teacher.getID().equals(id)) {
                return teacher;
            }
        }
        throw new IllegalArgumentException("No such teacher with this id (" + id + ")");
    }

}


