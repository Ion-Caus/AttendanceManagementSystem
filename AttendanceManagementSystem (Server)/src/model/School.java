package model;


import java.util.ArrayList;

/**
 * This class is representing the school
 */
public class School {
    private String name;
    private ClassList classList;
    private StudentList studentList;
    private LessonDataList lessonDataList;
    private TeacherList teacherList;

    public void setClassList(ArrayList<Class> classList) {
        this.classList = new ClassList(classList);
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = new StudentList(studentList);
    }

    public void setTeacherList(ArrayList<Teacher> teacherList) {
        this.teacherList = new TeacherList(teacherList);
    }

    public void setLessonDataList(ArrayList<LessonData> lessonDataList) {
        this.lessonDataList = new LessonDataList(lessonDataList);
    }

    /**
     * 0 argument constructor that will set the school name to "School" and initialize the rest of the elements as new element
     */
    public School() {
        this.name = "School";
        this.classList = new ClassList();
        this.studentList = new StudentList();
        this.lessonDataList = new LessonDataList();
        this.teacherList = new TeacherList();
    }

    /**1 parameter constructor that will call the previous constructor and initialize the school name according to the argument provided after
     * @param name the name of the school
     */
    public School(String name) {
        this();
        this.name = name;
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

    public LessonDataList getLessonDataList() {
        return lessonDataList;
    }

    public TeacherList getTeacherList() {
        return teacherList;
    }
}
