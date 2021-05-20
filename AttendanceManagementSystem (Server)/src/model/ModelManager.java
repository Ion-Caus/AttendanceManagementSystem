package model;

import dao.ClassesDAOImpl;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {
    private School school;
    private PropertyChangeHandler<String,String> property;

    public ModelManager() throws SQLException
    {
        school = new School();
        this.property = new PropertyChangeHandler<>(this);
        //createDummy();
    }

  /*  public void createDummy() throws SQLException
    {
        setSchoolName("DaVinci");
        StudentList studentList = school.getStudentList();
        studentList.addStudent(new Student("Ion Caus", "308234"));
        studentList.addStudent(new Student("Denis", "433234"));
        studentList.addStudent(new Student("Max", "308415"));
        studentList.addStudent(new Student("Tomas", "308400"));

        TeacherList teacherList = school.getTeacherList();
        Teacher steffen = new Teacher("Steffen Vissing Andersen", "325632");
        teacherList.addTeacher(steffen);

       ClassList classList = school.getClassList();
        Class class1 = new Class("12 C");
        Class class2 = new Class("11 A");

        classList.addClass(class1);
        classList.addClass(class2);

        class1.getStudents().addStudent(studentList.getAllStudents().get(0));
        studentList.getAllStudents().get(0).setClassName(class1.getClassName());

        class1.getStudents().addStudent(studentList.getAllStudents().get(1));
        studentList.getAllStudents().get(1).setClassName(class1.getClassName());

        class2.getStudents().addStudent(studentList.getAllStudents().get(2));
        studentList.getAllStudents().get(2).setClassName(class2.getClassName());

        Lesson lesson1 = new Lesson("12s1" ,steffen,
                new Date(), //now
                new Time(1,1,1),
                new Time(2,2,2),
                "Math",
                "Logarithms",
                "305A",
                "ex. 3,4,5 pag 6."
        );

        Lesson lesson2 = new Lesson("12s2" ,steffen,
                new Date(), //now
                new Time(3,1,1),
                new Time(4,2,2),
                "DBS",
                "Logarithms",
                "305A",
                "ex. 3,4,5 pag 6."
        );

        Lesson lesson3 = new Lesson("12s3" ,steffen,
                new Date(), //now
                new Time(5,1,1),
                new Time(6,2,2),
                "Java",
                "Logarithms",
                "305A",
                "ex. 3,4,5 pag 6."
        );

        class1.getSchedule().addLesson(lesson1);
        class1.getSchedule().addLesson(lesson2);
        class2.getSchedule().addLesson(lesson3);

        System.out.println(lesson1.getId());
        System.out.println(lesson2.getId());
        System.out.println(lesson3.getId());

    } */

    @Override
    public ArrayList<Class> getAllClasses() throws SQLException
    {
        return school.getClassList().getAllClasses();
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return school.getStudentList().getAllStudents();
    }

    @Override
    public ArrayList<String> getUnassignedStudents() {
        return school.getStudentList().getUnassignedStudents();
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return school.getTeacherList().getAllTeachers();
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Class theClass, LocalDate date) {
        return theClass.getSchedule().getLessonBy(date);
    }

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        return getClassWith(student).getSchedule().getLessonBy(date);
    }
    @Override
    public Class getClassWith(Student student) throws IllegalArgumentException {
        return school.getClassList().getClassWith(student);
    }
    @Override
    public Class getClassByName(String name) throws IllegalArgumentException {
        return school.getClassList().getClassByName(name);
    }

    @Override
    public Student getStudentBy(String id) throws IllegalArgumentException {
        return school.getStudentList().getStudentByID(id);
    }


    @Override
    public void addClass(String className)
        throws IllegalArgumentException, SQLException
    {
        school.getClassList().addClass(new Class(className));

        property.firePropertyChange("ADD Class", null, className);
    }

    @Override
    public void removeClass(String className)
        throws IllegalAccessException, SQLException
    {
        school.getClassList().removeClass(className);
        property.firePropertyChange("REMOVE Class", null, className);

    }

    @Override
    public void addStudent(String studentName, String studentID) throws IllegalArgumentException {
        school.getStudentList().addStudent(new Student(studentName, studentID));
        property.firePropertyChange("ADD Student", studentName, studentID);
    }

    @Override
    public void removeStudent(String studentID) throws IllegalArgumentException {
        //remove from class' studentList
        String className = getStudentBy(studentID).getClassName();
        if(className != null)
            school.getClassList().getClassByName(className).getStudents().removeStudent(studentID);
        //remove from school's studentList
        school.getStudentList().removeStudent(studentID);

        property.firePropertyChange("REMOVE Student", null, studentID);
    }

    @Override
    public void addStudentToClass(String studentID, String className) throws IllegalArgumentException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

            theClass.getStudents().addStudent(student);
            student.setClassName(theClass.getClassName());
            property.firePropertyChange("ADD Student Class", className, studentID);

        System.out.println("fire add");
    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

        student.clearClassName();
        theClass.getStudents().removeStudent(student);

        property.firePropertyChange("REMOVE Student Class", className, studentID);

        System.out.println("fire remove");
    }

    @Override
    public void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException {
        school.getTeacherList().addTeacher(new Teacher(teacherName, teacherID));
        property.firePropertyChange("ADD Teacher", teacherName, teacherID);
    }

    @Override
    public void removeTeacher(String teacherID) {
       //TODO 16/5 by Deniss handle removing the teacher from lessons or throw exception if teacher has lessons
        school.getTeacherList().removeTeacher(teacherID);
        property.firePropertyChange("REMOVE Teacher", null, teacherID);
    }

    @Override
    public String getClassAndSchool(Student student) {
        return student.getClassName() + ", " + getSchoolName();
    }

    @Override
    public void setSchoolName(String name) {
        school.setName(name);
    }

    @Override
    public String getSchoolName() {
        return school.getName();
    }

    @Override
    public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }
}
