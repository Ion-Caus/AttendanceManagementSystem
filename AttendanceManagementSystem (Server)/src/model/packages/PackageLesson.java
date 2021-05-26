package model.packages;

import model.Lesson;

import java.io.Serializable;

public class PackageLesson extends Package implements Serializable {
    private Lesson lesson;

    public PackageLesson(Lesson lesson) {
        super(lesson.getId());
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
