package model;


public class School {
    private String name;
    private ClassList classList;
    private StudentList studentList;
    private TeacherList teacherList;
    private AdminList adminList;



    public School() {
        this.name = "";
        this.classList = new ClassList();
        this.studentList = new StudentList();
        this.teacherList = new TeacherList();
        this.adminList = new AdminList();
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

    public TeacherList getTeacherList() {
        return teacherList;
    }

    public AdminList getAdminList() {
        return adminList;
    }
}
