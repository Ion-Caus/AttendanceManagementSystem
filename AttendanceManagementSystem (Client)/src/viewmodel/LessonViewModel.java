package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LessonViewModel {
    private StringProperty subject;
    private StringProperty title;
    private StringProperty teacher;
    private StringProperty time;
    private StringProperty classroom;

    public LessonViewModel(Lesson lesson) {
        subject = new SimpleStringProperty(lesson.getSubject());
        title = new SimpleStringProperty(lesson.getTitle());
        teacher = new SimpleStringProperty(lesson.getTeacher().getName());
        time = new SimpleStringProperty(lesson.getTimeInterval());
        classroom = new SimpleStringProperty(lesson.getClassroom());
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty teacherProperty() {
        return teacher;
    }

    public StringProperty timeProperty() {
        return time;
    }


    public StringProperty classroomProperty() {
        return classroom;
    }



}
