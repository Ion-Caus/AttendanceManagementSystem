package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {
    private School school;

    public ModelManager() {
        school = new School();
        createDummy();
    }

    public void createDummy() {
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

        class1.getSchedule().addLesson(
                new Lesson(new Teacher("Steffen", "SVA", "325632"),
                        new Date(2021,4,29),
                        new Time(1,1,1),
                        new Time(2,2,2),
                        "Math",
                        "Logarithms",
                        "305A",
                        "ex. 3,4,5 pag 6."
                )
        );
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

    @Override
    public ArrayList<Lesson> getScheduleFor(Student student, LocalDate date) {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        for (Class aClass: school.getClassList().getAllClasses()) {
            for (Student aStudent: aClass.getStudents().getAllStudents()) {
                if (aStudent.equals(student)) {
                    for (Lesson lesson: aClass.getSchedule().getAllLessons()) {
                        if (lesson.getLessonDate().getDate().equals(date)) {
                            lessonList.add(lesson);
                            System.out.println( lesson.getTimeInterval() );
                        }
                    }
                }
            }
        }
        return lessonList;
    }

    @Override
    public Student getStudentBy(String id) {
        for (Class aClass: school.getClassList().getAllClasses()) {
            for (Student student: aClass.getStudents().getAllStudents()) {
                if (student.getID().equals(id)) {
                    return student;
                }
            }
        }
        //TODO throw exception
        return null;
    }
    // ---

    @Override
    public void setSchoolName(String name) {
        school.setName(name);
    }
}
