package model;

import java.util.ArrayList;

public class ModelManager implements Model {
    private School school;

    public ModelManager() {
        school = new School();
        createDummy();
    }

    public void createDummy() {
        school.setName("DaVinci");

        StudentList studentList = school.getStudentList();
        studentList.addStudent(new Student("Ion Caus", "308234"));
        studentList.addStudent(new Student("Denis", "4338234"));
        studentList.addStudent(new Student("Max", "308415"));

        ClassList classList = school.getClassList();
        Class class1 = new Class("12 C");
        Class class2 = new Class("11 A");

        classList.addClass(class1);
        classList.addClass(class2);

        class1.getStudents().addStudent(studentList.getAllStudents().get(0));
        class1.getStudents().addStudent(studentList.getAllStudents().get(1));

        class2.getStudents().addStudent(studentList.getAllStudents().get(2));
    }

    // Getters for Lists
    @Override
    public ArrayList<Class> getAllClasses() {
        return school.getClassList().getAllClasses();
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return school.getStudentList().getAllStudents();
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() throws NullPointerException {
        return null;
    }
    // ---

    @Override
    public void setSchoolName(String name) {
        school.setName(name);
    }
}
