package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private ArrayList<Lesson> schedule;

    public Schedule() {
        this.schedule = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        schedule.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        schedule.remove(lesson);
    }

    public Lesson getLessonBy(String lessonId) {
        for (Lesson lesson : schedule) {
            if (lesson.getId().equals(lessonId))
                return lesson;
        }
        throw new IllegalArgumentException("No such lesson with this id (" + lessonId + ")");
    }

    public ArrayList<Lesson> getLessonBy(LocalDate date) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (Lesson lesson : schedule) {
            if (lesson.getLessonDate().equals(date))
                lessons.add(lesson);
        }
        return lessons;
    }

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

    public ArrayList<Lesson> getAllLessons() {
        return schedule;
    }
}
