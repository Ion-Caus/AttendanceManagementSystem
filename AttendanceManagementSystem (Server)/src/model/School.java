package model;


public class School {
    private String name;
    private ClassList classList;
    private StudentList studentList;
    private LessonDataList lessonDataList;
    private TeacherList teacherList;

    public School() {
        this.name = "School";
        this.classList = new ClassList();
        this.studentList = new StudentList();
        this.lessonDataList = new LessonDataList();
        this.teacherList = new TeacherList();
    }

    public School(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(name);
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
