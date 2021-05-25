package model.packages;

import model.Lesson;

public class PackageLesson extends Package{
    private Lesson lesson;

    public PackageLesson(Lesson lesson) {
        super(lesson.getId());
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
