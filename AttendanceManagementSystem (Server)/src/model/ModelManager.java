package model;

import dao.*;
import model.packages.Package;
import model.packages.PackageAbsence;
import model.packages.PackageLesson;
import model.packages.PackageName;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ModelManager implements Model {
    private School school;
    private PropertyChangeHandler<String, Package> property;

    public ModelManager() throws SQLException {
        school = new School();
        this.property = new PropertyChangeHandler<>(this);
        loadFromDatabase();
    }

    private void loadFromDatabase() throws SQLException {
       school.setClassList(ClassesDAOImpl.getInstance().readClasses());
       school.setStudentList(UserAccountsDAOImpl.getInstance().readAllStudents());
       school.setTeacherList(UserAccountsDAOImpl.getInstance().readTeachers());
       school.setLessonDataList(LessonDataDAOImpl.getInstance().readAll());
       ArrayList<Lesson> lessonList = ScheduleDAOImpl.getInstance().readAll();
       loadStudentsInClasses();
       loadLessonsInClasses(lessonList);
        System.out.println(school.getLessonDataList().getLessonDataList());
    }

    private void loadLessonsInClasses(ArrayList<Lesson> lessonList){
        for(Class aClass : getAllClasses())
            for(Lesson lesson : lessonList)
                if(Objects.equals(aClass.getClassName(),lesson.getClassName()))
                    aClass.getSchedule().getAllLessons().add(lesson);
    }

    private void loadStudentsInClasses() {
        for(Class aClass : getAllClasses())
            for(Student student: getStudentsByClass(aClass.getClassName()))
                aClass.getStudents().getAllStudents().add(student);
    }

    public void createDummy() {
//        setSchoolName("DaVinci");
//        StudentList studentList = school.getStudentList();
//        studentList.addStudent(new Student("Ion Caus", "308234"));
//        studentList.addStudent(new Student("Denis", "433234"));
//        studentList.addStudent(new Student("Max", "308415"));
//        studentList.addStudent(new Student("Tomas", "308400"));
//
//        TeacherList teacherList = school.getTeacherList();
//        Teacher steffen = new Teacher("Steffen Vissing Andersen", "325632");
//        teacherList.addTeacher(steffen);
//        Teacher ole = new Teacher("Ole Ildsgaard Hougaard", "325600");
//        teacherList.addTeacher(ole);
//
//
//        ClassList classList = school.getClassList();
//        Class class1 = new Class("12 C");
//        Class class2 = new Class("11 A");
//
//        classList.addClass(class1);
//        classList.addClass(class2);
//
//        class1.getStudents().addStudent(studentList.getAllStudents().get(0));
//        studentList.getAllStudents().get(0).setClassName(class1.getClassName());
//
//        class1.getStudents().addStudent(studentList.getAllStudents().get(1));
//        studentList.getAllStudents().get(1).setClassName(class1.getClassName());
//
//        class2.getStudents().addStudent(studentList.getAllStudents().get(2));
//        studentList.getAllStudents().get(2).setClassName(class2.getClassName());
//
//        Lesson lesson1 = new Lesson(id, ole,
//                new Date(), //now
//                new Time(9,20,0),
//                new Time(10,30,0),
//                "DBS",
//                "Stating with Databases",
//                "set up a database",
//                "305A",
//                "Download Postgres"
//        );
//
//        Lesson lesson2 = new Lesson(id, steffen,
//                new Date(), //now
//                new Time(10,30,0),
//                new Time(11,45,0),
//                "Java",
//                "Threads",
//                "Counter Incrementer exercise",
//                "Zoom",
//                "dont be drunk on lesson"
//        );
//
//        Lesson lesson3 = new Lesson(id, ole,
//                new Date(), //now
//                new Time(12,45,0),
//                new Time(14,15,0),
//                "DBS",
//                "ER Diagrams",
//                "Hospital exercise",
//                "305A",
//                "bring paper and pen"
//        );
//
//        Lesson lesson4 = new Lesson(id, steffen,
//                new Date(),
//                new Time(14,30,0),
//                new Time(16,0,0),
//                "Java",
//                "Observer",
//                "Observer Pattern exercises",
//                "305A",
//                "be on time"
//        );
//
//        class1.getSchedule().addLesson(lesson1);
//        lesson1.setClassName(class1.getClassName());
//
//        class1.getSchedule().addLesson(lesson2);
//        lesson2.setClassName(class1.getClassName());
//
//        class1.getSchedule().addLesson(lesson4);
//        lesson4.setClassName(class1.getClassName());
//
//        class2.getSchedule().addLesson(lesson3);
//        lesson3.setClassName(class2.getClassName());
//


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
    public Lesson getLesson(String lessonID) throws IllegalArgumentException {
        for (Class aClass: getAllClasses()) {
            for (Lesson lesson: aClass.getSchedule().getAllLessons()) {
                if (lesson.getId().equals(lessonID)) {
                    return lesson;
                }
            }
        }
        throw new IllegalArgumentException("No such lesson with the id (" + lessonID + ")");
    }

    @Override
    public LessonData getLessonData(Lesson lesson, Student student) {
        try {
            System.out.println(school.getLessonDataList().getLessonDataList());
            return school.getLessonDataList().getByStudentAndLesson(lesson, student);
        }
        catch (IllegalArgumentException e) {
            school.getLessonDataList().addLessonData(new LessonData(lesson, student));
            // TODO: 5/22/2021 Add Lesson Data to DAO here 
            return school.getLessonDataList().getByStudentAndLesson(lesson, student);
        }
    }

    @Override
    public void addClass(String className) throws IllegalArgumentException, SQLException {
        var aClass = new Class(className);
        school.getClassList().addClass(aClass);
        ClassesDAOImpl.getInstance().addClass(aClass);
        property.firePropertyChange("ADD Class", null, new PackageName(className,null));
    }

    @Override
    public void removeClass(String className) throws IllegalAccessException, SQLException {
        school.getClassList().removeClass(className);
        ClassesDAOImpl.getInstance().removeClass(className);
        property.firePropertyChange("REMOVE Class", null, new PackageName(className,null));

    }

    @Override
    public void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException {
        school.getStudentList().addStudent(new Student(studentName, studentID));
        UserAccountsDAOImpl.getInstance().createUserAccount(studentName,studentID,"default",UserAccountsDAOImpl.STUDENT);
        property.firePropertyChange("ADD Student", null, new PackageName(studentID, studentName));
    }

    @Override
    public void removeStudent(String studentID) throws IllegalArgumentException, SQLException {
        //remove from class' studentList
        String className = getStudentBy(studentID).getClassName();

        for(LessonData ld : school.getLessonDataList().getLessonDataList())
            if(Objects.equals(ld.getStudent().getID(),studentID))
                school.getLessonDataList().removeLessonData(ld);

        if(className != null) {
            school.getClassList().getClassByName(className).getStudents().removeStudent(studentID);
        }
        //remove from school's studentList
        school.getStudentList().removeStudent(studentID);
        UserAccountsDAOImpl.getInstance().deleteUser(studentID);

        property.firePropertyChange("REMOVE Student", null, new Package(studentID));
    }

    @Override
    public void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

        theClass.getStudents().addStudent(student);
        student.setClassName(theClass.getClassName());

        StudentListDAOImpl.getInstance().addToClass(className,studentID);

        property.firePropertyChange("ADD_TO_CLASS Student", null,  new PackageName(studentID, className));
    }

    @Override
    public void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

        theClass.getStudents().removeStudent(student);
        student.clearClassName();

        StudentListDAOImpl.getInstance().removeFromClass(className,studentID);

        property.firePropertyChange("REMOVE_FROM_CLASS Student", null, new PackageName(studentID, className));
    }

    @Override
    public void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException {
        school.getTeacherList().addTeacher(new Teacher(teacherName, teacherID));
        UserAccountsDAOImpl.getInstance().createUserAccount(teacherName,teacherID,"default",UserAccountsDAOImpl.TEACHER);
        property.firePropertyChange("ADD Teacher", null, new PackageName(teacherID, teacherName));
    }

    @Override
    public void removeTeacher(String teacherID) throws SQLException {
       //TODO 16/5 by Deniss handle removing the teacher from lessons or throw exception if teacher has lessons
        school.getTeacherList().removeTeacher(teacherID);
        UserAccountsDAOImpl.getInstance().deleteUser(teacherID);
        property.firePropertyChange("REMOVE Teacher", null, new Package(teacherID));
    }

    //--
    @Override
    public boolean changeMotive(String studentId, String lessonID, String motive) {
        getLessonData(getLesson(lessonID), getStudentBy(studentId)).getAbsence().setMotive(motive);
        property.firePropertyChange("ChangeMotive", null, new PackageAbsence(studentId, lessonID,motive));
        return true;
    }

    @Override
    public boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException {
        LessonData ld = getLessonData(getLesson(lessonID), getStudentBy(studentID));
        ld.getAbsence().setWasAbsent(!absence);
        LessonDataDAOImpl.getInstance().updateAbsenceStatus(ld);
        property.firePropertyChange("ChangeAbsence", null, new PackageAbsence(studentID, lessonID, absence));
        return !absence;
    }

    @Override
    public boolean changeLesson(String lessonID, String topic, String contents, String homework) {
        Lesson lesson = getLesson(lessonID);
        lesson.setTopic(topic);
        lesson.setContents(contents);
        lesson.setHomework(homework);

        property.firePropertyChange("ChangeLesson", null, new PackageLesson(lessonID, topic, contents, homework));
        return true;
    }
    //--

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
    public boolean addListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }
}
