package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import model.Lesson;

public class LessonViewModel {
    private StringProperty id;
    private StringProperty subject;
    private StringProperty topic;
    private StringProperty teacher;
    private StringProperty time;
    private StringProperty classroom;

    public LessonViewModel(Lesson lesson) {
        id = new SimpleStringProperty(lesson.getId());
        subject = new SimpleStringProperty(lesson.getSubject());
        topic = new SimpleStringProperty(lesson.getTopic());
        teacher = new SimpleStringProperty(lesson.getTeacher().getInitials());
        time = new SimpleStringProperty(lesson.getTimeInterval());
        classroom = new SimpleStringProperty(lesson.getClassroom());
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty topicProperty() {
        return topic;
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
