package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {
    private School school;
    private PropertyChangeHandler<String,String> property;

    public ModelManager() {
        school = new School();
        this.property = new PropertyChangeHandler<>(this);
        createDummy();
    }

    public void createDummy() {
        setSchoolName("DaVinci");
        StudentList studentList = school.getStudentList();
        studentList.addStudent(new Student("Ion Caus", "308234"));
        studentList.addStudent(new Student("Denis", "433234"));
        studentList.addStudent(new Student("Max", "308415"));
        studentList.addStudent(new Student("Tomas", "308400"));

        TeacherList teacherList = school.getTeacherList();
        Teacher steffen = new Teacher("Steffen Vissing Andersen", "325632");
        teacherList.addTeacher(steffen);
        Teacher ole = new Teacher("Ole Ildsgaard Hougaard", "325600");
        teacherList.addTeacher(ole);


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

        Lesson lesson1 = new Lesson(ole,
                new Date(), //now
                new Time(9,20,0),
                new Time(10,30,0),
                "DBS",
                "Stating with Databases",
                "305A",
                "Download Postgres",
                "set up a database"
        );

        Lesson lesson2 = new Lesson(steffen,
                new Date(), //now
                new Time(10,30,0),
                new Time(11,45,0),
                "Java",
                "Threads",
                "Zoom",
                "Counter Incrementer exercise",
                "dont be drunk on lesson"
        );

        Lesson lesson3 = new Lesson(ole,
                new Date(), //now
                new Time(12,45,0),
                new Time(14,15,0),
                "DBS",
                "ER Diagrams",
                "305A",
                "Hospital exercise",
                "bring paper and pen"
        );

        Lesson lesson4 = new Lesson(steffen,
                new Date(),
                new Time(14,30,0),
                new Time(16,0,0),
                "Java",
                "Observer",
                "305A",
                "Observer Pattern exercises",
                "be on time"
        );

        class1.getSchedule().addLesson(lesson1);
        lesson1.setClassName(class1.getClassName());

        class1.getSchedule().addLesson(lesson2);
        lesson2.setClassName(class1.getClassName());

        class1.getSchedule().addLesson(lesson4);
        lesson4.setClassName(class1.getClassName());

        class2.getSchedule().addLesson(lesson3);
        lesson3.setClassName(class2.getClassName());



    }

    @Override
    public ArrayList<Class> getAllClasses() {
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
    public ArrayList<Student> getStudentsByClass(String className) {
        return school.getStudentList().getStudentsByClass(className);
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
    public ArrayList<Lesson> getScheduleFor(Teacher teacher, LocalDate date) {
        ArrayList<Lesson> lessons = new ArrayList<>();

        for (Class aClass: getAllClasses()) {
            lessons.addAll(aClass.getSchedule().getLessonBy(teacher, date));
        }
        return lessons;
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
    public Teacher getTeacherBy(String id) throws IllegalArgumentException {
        return school.getTeacherList().getTeacherByID(id);
    }

    @Override
    public Lesson getLesson(String lessonID, Student student) throws IllegalArgumentException {
        return getClassByName(student.getClassName()).getSchedule().getLessonBy(lessonID);
    }

    @Override
    public Lesson getLesson(String lessonID, Class aClass) throws IllegalArgumentException {
        return aClass.getSchedule().getLessonBy(lessonID);
    }

    @Override
    public Lesson getLesson(String lessonID, Teacher teacher) throws IllegalArgumentException {
        //TODO 18/05 by Ion find a way to get the lesson by ID for the teacher
        return null;
    }

    @Override
    public LessonData getLessonData(Lesson lesson, Student student) {
        try {
            return school.getLessonDataList().getByStudentAndLesson(lesson, student);
        }
        catch (IllegalArgumentException e) {
            school.getLessonDataList().addLessonData(new LessonData(lesson, student));
            return school.getLessonDataList().getByStudentAndLesson(lesson, student);
        }
    }


    @Override
    public void addClass(String className) throws IllegalArgumentException {
        school.getClassList().addClass(new Class(className));

        property.firePropertyChange("ADD Class", null, className);
    }

    @Override
    public void removeClass(String className) throws IllegalAccessException {
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

        property.firePropertyChange("ADD_TO_CLASS Student", className, studentID);
    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

        theClass.getStudents().removeStudent(student);
        student.clearClassName();

        property.firePropertyChange("REMOVE_FROM_CLASS Student", className, studentID);
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
