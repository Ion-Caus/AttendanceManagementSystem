package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Lesson;
import model.Model;

import java.time.LocalDate;

public class InfoViewModel {
    private StringProperty subject;
    private StringProperty topic;
    private StringProperty contents;
    private StringProperty homework;
    private StringProperty teacher;
    private ObjectProperty<LocalDate> date;

    private StringProperty error;
    private StringProperty className;

    private StringProperty absent;
    private StringProperty motive;

    //TODO 13/5 by Ion Make this IntegerProperty? in my opinion is not needed
    private StringProperty grade;
    private StringProperty comment;

    private Model model;
    private ViewModelState viewState;

    public InfoViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewState = viewModelState;

        //Lesson properties
        this.subject = new SimpleStringProperty();
        this.topic = new SimpleStringProperty();
        this.contents = new SimpleStringProperty();
        this.homework = new SimpleStringProperty();
        this.teacher = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        //--

        this.className = new SimpleStringProperty();
        this.error = new SimpleStringProperty();

        //Student View properties
        this.absent = new SimpleStringProperty();
        this.motive = new SimpleStringProperty();
        this.grade = new SimpleStringProperty();
        this.comment = new SimpleStringProperty();
        //--

    }

    public StringProperty getSubjectProperty() {
        return subject;
    }

    public StringProperty getTopicProperty() {
        return topic;
    }

    public StringProperty getContentsProperty() {
        return contents;
    }

    public StringProperty getHomeworkProperty() {
        return homework;
    }

    public StringProperty getTeacherProperty() {
        return teacher;
    }

    public ObjectProperty<LocalDate> getDateProperty() {
        return date;
    }

    public StringProperty getClassNameProperty() {
        return className;
    }

    public StringProperty getErrorProperty() {
        return error;
    }

    public StringProperty getAbsentProperty() {
        return absent;
    }

    public StringProperty getMotiveProperty() {
        return motive;
    }

    public StringProperty getGradeProperty() {
        return grade;
    }

    public StringProperty getCommentProperty() {
        return comment;
    }

    public String getViewStateAccessLevel() {
        return viewState.getAccessLevel();
    }

    public void loadFromModel() {




        /*Lesson lesson = model.getLessonBy(viewState.getLessonID());
        subject.set(lesson.getSubject());
        topic.set(lesson.getTopic());
*/
    }

    public void clear() {
        //TODO clear the rest property
        //Lesson lesson = viewState.getLessonID()
        //className.set(model.getLessonBy());

    }

    // TODO
}
