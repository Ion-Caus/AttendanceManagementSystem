package model;

import dao.*;
import external.Log;
import model.packages.*;
import model.packages.Package;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ModelManager implements Model {
    private School school;
    private PropertyChangeHandler<String, Package> property;


    private ClassesDAO classesDAO;
    private LessonDataDAO lessonDataDAO;
    private LessonDAO lessonDAO;
    private ScheduleDAO scheduleDAO;
    private StudentListDAO studentListDAO;
    private UserAccountsDAO userAccountsDAO;

    public ModelManager() throws SQLException {
        this.classesDAO = ClassesDAOImpl.getInstance();
        this.userAccountsDAO = UserAccountsDAOImpl.getInstance();
        this.lessonDataDAO = LessonDataDAOImpl.getInstance();
        this.scheduleDAO = ScheduleDAOImpl.getInstance();
        this.studentListDAO = StudentListDAOImpl.getInstance();
        this.lessonDAO = LessonDAOImpl.getInstance();

        school = new School();
        this.property = new PropertyChangeHandler<>(this);
        loadFromDatabase();
    }

    private void loadFromDatabase() throws SQLException {
       school.setClassList(classesDAO.readClasses());
       school.setStudentList(userAccountsDAO.readAllStudents());
       school.setTeacherList(userAccountsDAO.readTeachers());
       school.setLessonDataList(lessonDataDAO.readAll());
       ArrayList<Lesson> lessonList = scheduleDAO.readAll();
       loadStudentsInClasses();
       loadLessonsInClasses(lessonList);
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

    @Override
    public String login(String userID, String password) throws IllegalAccessException, SQLException {
        String access = userAccountsDAO.login(userID, password);
        if ( access.equals("student") &&
                getStudentBy(userID).getClassName() == null ) {
            throw new IllegalAccessException("You are not yet assigned to a class. Please contact the administration.");
        }
        Log.getLog().addLog(String.format("User with the id (%s), logged in as %s", userID, access));
        return access;
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
            return school.getLessonDataList().getByStudentAndLesson(lesson, student);
        } catch (IllegalArgumentException e) {
            try {
                lessonDataDAO.createLessonData(lesson.getId(), student.getID());
                school.getLessonDataList().addLessonData(new LessonData(lesson, student));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return school.getLessonDataList().getByStudentAndLesson(lesson, student);
        }
    }

    @Override
    public synchronized void addClass(String className) throws IllegalArgumentException, SQLException {
        var aClass = new Class(className);
        school.getClassList().addClass(aClass);
        classesDAO.addClass(aClass);
        property.firePropertyChange("ADD Class", null, new PackageName(className,null));
        Log.getLog().addLog(String.format("The class (%s) has been added.", className));
    }

    @Override
    public synchronized void removeClass(String className) throws IllegalAccessException, SQLException {
        school.getClassList().removeClass(className);
        classesDAO.removeClass(className);
        property.firePropertyChange("REMOVE Class", null, new PackageName(className,null));
        Log.getLog().addLog(String.format("The class (%s) has been removed.", className));
    }

    @Override
    public synchronized void addStudent(String studentName, String studentID) throws IllegalArgumentException, SQLException {
        school.getStudentList().addStudent(new Student(studentName, studentID));
        userAccountsDAO.createUserAccount(studentName,studentID,"default",UserAccountsDAOImpl.STUDENT);
        property.firePropertyChange("ADD Student", null, new PackageName(studentID, studentName));
        Log.getLog().addLog(String.format("The student with the id (%s) has been added.", studentID));
    }

    @Override
    public synchronized void removeStudent(String studentID) throws IllegalArgumentException, SQLException {
        //remove from class' studentList
        school.getLessonDataList().removeLessonDataByStudent(studentID);

        String className = getStudentBy(studentID).getClassName();
        if(className != null) {
            school.getClassList().getClassByName(className).getStudents().removeStudent(studentID);
        }
        //remove from school's studentList
        school.getStudentList().removeStudent(studentID);
        userAccountsDAO.deleteUser(studentID);

        property.firePropertyChange("REMOVE Student", null, new Package(studentID));
        Log.getLog().addLog(String.format("The student with the id (%s) has been removed.", studentID));
    }

    @Override
    public synchronized void addStudentToClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

        theClass.getStudents().addStudent(student);
        student.setClassName(theClass.getClassName());

        studentListDAO.addToClass(className,studentID);

        property.firePropertyChange("ADD_TO_CLASS Student", null,  new PackageName(studentID, className));
        Log.getLog().addLog(String.format("The student with the id (%s) has been added to the class (%s).", studentID, className));
    }

    @Override
    public synchronized void removeStudentFromClass(String studentID, String className) throws IllegalArgumentException, SQLException {
        Class theClass = getClassByName(className);
        Student student = getStudentBy(studentID);

        theClass.getStudents().removeStudent(student);
        student.clearClassName();

        studentListDAO.removeFromClass(className,studentID);

        property.firePropertyChange("REMOVE_FROM_CLASS Student", null, new PackageName(studentID, className));
        Log.getLog().addLog(String.format("The student with the id (%s) has been removed from the class (%s).", studentID, className));
    }

    @Override
    public synchronized void addTeacher(String teacherName, String teacherID) throws IllegalArgumentException, SQLException {
        school.getTeacherList().addTeacher(new Teacher(teacherName, teacherID));
        userAccountsDAO.createUserAccount(teacherName,teacherID,"default",UserAccountsDAOImpl.TEACHER);
        property.firePropertyChange("ADD Teacher", null, new PackageName(teacherID, teacherName));
        Log.getLog().addLog(String.format("The teacher with the id (%s) has been added.", teacherID));
    }

    @Override
    public synchronized void removeTeacher(String teacherID) throws SQLException {
        ArrayList<Lesson> lessons = getLessonsByTeacher(teacherID);
        lessons.forEach(lesson -> lesson.setTeacher(getTeacherBy("000000")));
        school.getTeacherList().removeTeacher(teacherID);

        userAccountsDAO.deleteTeacher(teacherID);

        property.firePropertyChange("REMOVE Teacher", null, new Package(teacherID));
        Log.getLog().addLog(String.format("The teacher with the id (%s) has been removed.", teacherID));
    }

    private ArrayList<Lesson> getLessonsByTeacher(String teacherID) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for(Class temp : getAllClasses())
            for(Lesson lesson : temp.getSchedule().getAllLessons())
                if(lesson.getTeacher().getID().equals(teacherID))
                    lessons.add(lesson);
        return lessons;
    }

    @Override
    public synchronized void addLesson(String className, Lesson lesson) throws SQLException {
        lessonDAO.createLesson(className,lesson);
        getClassByName(className).getSchedule().addLesson(lesson);
        property.firePropertyChange("ADD Lesson", null, new PackageLesson(lesson));
        Log.getLog().addLog(String.format("The lesson with the id (%s) has been added to the class (%s).", lesson.getId(), className));
    }

    @Override
    public synchronized void removeLesson(String className, String lessonID) throws SQLException, IllegalArgumentException {
        Schedule schedule = getClassByName(className).getSchedule();
        Lesson lesson = schedule.getLessonBy(lessonID);
        lessonDAO.delete(lessonID);
        schedule.removeLesson(lesson);
        property.firePropertyChange("REMOVE Lesson", null, new PackageName(lessonID, className));
        Log.getLog().addLog(String.format("The lesson with the id (%s) has been removed from the class (%s).", lessonID, className));
    }

    @Override
    public synchronized void changeGradeComment(String studentID, String lessonID, int grade, String comment) throws SQLException, IllegalArgumentException {
        LessonData lessonData = getLessonData(
                getLesson(lessonID),
                getStudentBy(studentID)
        );
        lessonData.setGrade(new Grade(grade, comment));
        lessonDataDAO.updateGradeComment(lessonData);

        property.firePropertyChange("ChangeGradeComment", null, new PackageGrade(studentID, lessonID, grade, comment));
        Log.getLog().addLog(String.format("<Change> LessonID: %s , StudentID: %s --> Grade: %d, Comment: %s", lessonID, studentID, grade, comment));
    }

    @Override
    public synchronized void changePassword(String userID, String password) throws IllegalArgumentException, SQLException {
        Password pw = new Password(password);
        userAccountsDAO.updatePassword(userID ,pw.getPassword());
        Log.getLog().addLog(String.format("User with the id (%s) changed his password.", userID) );
    }

    //--
    @Override
    public synchronized boolean changeMotive(String studentId, String lessonID, String motive) throws SQLException {
        LessonData ld = getLessonData(getLesson(lessonID), getStudentBy(studentId));
        ld.getAbsence().setMotive(motive);
        lessonDataDAO.updateAbsenceMotive(ld);
        property.firePropertyChange("ChangeMotive", null, new PackageAbsence(studentId, lessonID,motive));
        Log.getLog().addLog(String.format("<Change> LessonID: %s , StudentID: %s --> Motive: %s", lessonID, studentId, motive));
        return true;
    }

    @Override
    public synchronized boolean changeAbsence(String studentID, String lessonID, boolean absence) throws SQLException {
        LessonData ld = getLessonData(getLesson(lessonID), getStudentBy(studentID));
        ld.getAbsence().setWasAbsent(!absence);
        lessonDataDAO.updateAbsenceStatus(ld);
        property.firePropertyChange("ChangeAbsence", null, new PackageAbsence(studentID, lessonID, !absence));
        Log.getLog().addLog(String.format("<Change> LessonID: %s , StudentID: %s --> Absence: %s", lessonID, studentID, !absence));
        return !absence;
    }

    @Override
    public synchronized boolean changeLesson(String lessonID, String topic, String contents, String homework, String teacherID) throws SQLException {
        Lesson lesson = getLesson(lessonID);
        lesson.setTopic(topic);
        lesson.setContents(contents);
        lesson.setHomework(homework);
        Teacher teacher = getTeacherBy(teacherID);
        lesson.setTeacher(teacher);
        lessonDAO.updateLesson(lesson,teacher,topic,contents,homework);
        property.firePropertyChange("ChangeLesson", null, new PackageLessonInfo(lessonID, topic, contents, homework, teacherID));
        Log.getLog().addLog(String.format("<Change> The lesson with id (%s) has been modified --> Topic: %s, Contents: %s, Homework: %s, TeacherID: %s", lessonID, topic, contents, homework, teacherID));
        return true;
    }
    //--

    @Override
    public boolean addListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }


}
