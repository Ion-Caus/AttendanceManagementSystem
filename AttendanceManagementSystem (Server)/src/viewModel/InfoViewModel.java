package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Lesson;
import model.LessonData;
import model.Model;
import model.Student;

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

    public void loadLessonFromModel() {
        Lesson lesson = null;
        switch (viewState.getSection()) {
            case "Student":
                Student student = model.getStudentBy(viewState.getID());
                lesson = model.getLesson(viewState.getLessonID(), student);

                loadLessonDataFroStudent(lesson, student);
                break;
            case "Teacher":
//                lesson = model.getLesson(
//                        viewState.getLessonID(),
//                        model.getTeacherBy(viewState.getID())
//                );
                break;
            case "Class":
                lesson = model.getLesson(
                        viewState.getLessonID(),
                        model.getClassByName(viewState.getID())
                );
                break;
        }


        assert lesson != null;
        subject.set(lesson.getSubject());
        topic.set(lesson.getTopic());
        //TODO 18/5 by Ion add contents to Lesson Class
        //contents.set(lesson.);
        homework.set(lesson.getHomework());
        teacher.set(lesson.getTeacher().getName());
        date.set(lesson.getLessonDate().getDate());
        className.set("Class " + lesson.getClassName());
    }

    public void loadLessonDataFroStudent(Lesson lesson, Student student) {
        LessonData data = model.getLessonData(lesson, student);

        if (data.getAbsence() != null) {
            absent.set( Boolean.toString( data.getAbsence().isWasAbsent() ));
            motive.set(data.getAbsence().getMotive());
        }

        if (data.getGrade() != null) {
            grade.set( Integer.toString( data.getGrade().getGrade() ));
            comment.set(data.getGrade().getComment());
        }

    }


    public void clear() {
        //TODO clear the rest property
        //Lesson lesson = viewState.getLessonID()
        //className.set(model.getLessonBy());

    }

    public void openStudentList() {

    }

    public boolean submitChangeLesson() {
        switch (viewState.getAccessLevel()) {
            case "Student":
                break;
            case "Administrator":
                break;
        }
        //TODO use observer to change lesson for all
        return false;
    }
}
