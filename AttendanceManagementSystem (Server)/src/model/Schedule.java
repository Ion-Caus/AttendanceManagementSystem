package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents a schedule associated to class and it works like a containing element for lessons
 */
public class Schedule implements Serializable {
    private ArrayList<Lesson> schedule;

    /** 0 argument constructor that initializes the schedule ArrayList as a new ArrayList
     *
     */
    public Schedule() {
        this.schedule = new ArrayList<>();
    }

    /**This method will add lesson provided as an argument to the schedule (lesson list)
     * @param lesson the lesson that is supposed to be added to schedule (lesson list)
     */
    public void addLesson(Lesson lesson) {
        schedule.add(lesson);
    }

    /** This method will remove lesson provided as an argument from the schedule (lesson list)
     * @param lesson
     */
    public void removeLesson(Lesson lesson) {
        schedule.remove(lesson);
    }

    /**The purpose of this method is to get lesson by lessonID
     * @param lessonId the lesson identification key
     * @return Lesson that has matching lessonId with the lessonId provided as an argument
     * @exception IllegalArgumentException will be thrown in case no lesson is matching with the lesson id provided as a parameter
     */
    public Lesson getLessonBy(String lessonId) {
        for (Lesson lesson : schedule) {
            if (lesson.getId().equals(lessonId))
                return lesson;
        }
        throw new IllegalArgumentException("No such lesson with this id (" + lessonId + ")");
    }

    /**The purpose of this method is to get lesson by date
     * @param date the date provided as an argument
     * @return Lesson that has matching date with the date provided as an argument
     */
    public ArrayList<Lesson> getLessonBy(LocalDate date) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (Lesson lesson : schedule) {
            if (lesson.getLessonDate().equals(date))
                lessons.add(lesson);
        }
        return lessons;
    }

    /**The purpose of this method is to get Lesson based on Teacher and date
     * @param teacher the teacher that is associated with the lesson
     * @param date the date provided as an argument
     * @return Lesson that has matching date and Teacher with date and Teacher provided as arguments
     */
    public ArrayList<Lesson> getLessonBy(Teacher teacher, LocalDate date) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (Lesson lesson : schedule) {
            if (lesson.getTeacher().equals(teacher) &&
                lesson.getLessonDate().equals(date)) {

                lessons.add(lesson);
            }
        }
        return lessons;
    }

    /**
     * @return list of all Lessons - Schedule
     */
    public ArrayList<Lesson> getAllLessons() {
        return schedule;
    }
}
