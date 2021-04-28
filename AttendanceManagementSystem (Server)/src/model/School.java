package model;


public class School {
    private String name;
    private ClassList classList;
    private StudentList studentList;
    //TODO add TeacherList class and the list here
    //private TeacherList teacherList;

    public School() {
        this.name = "";
        this.classList = new ClassList();
        this.studentList = new StudentList();
    }

    public School(String name) {
        this.name = name;
        this.classList = new ClassList();
        this.studentList = new StudentList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassList getClassList() {
        return classList;
    }

    public StudentList getStudentList() {
        return studentList;
    }
}
