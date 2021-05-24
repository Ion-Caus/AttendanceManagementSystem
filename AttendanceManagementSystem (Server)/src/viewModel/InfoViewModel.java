package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Lesson;
import model.LessonData;
import model.Model;
import model.Student;
import model.packages.Package;
import model.packages.PackageGrade;
import model.packages.PackageName;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.sql.SQLException;
import java.time.LocalDate;

public class InfoViewModel implements LocalListener<String, Package> {
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

    private StringProperty grade;
    private StringProperty comment;

    private Model model;
    private ViewModelState viewState;

    public InfoViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.model.addListener(this, "ChangeGradeComment");
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
                Student student = model.getStudentBy(viewState.getStudentID());
                lesson = model.getLesson(viewState.getLessonID(), student);

                loadLessonDataFromStudent(lesson, student);
                break;
            case "Teacher":
                lesson = model.getLesson(
                        viewState.getLessonID()
                );
                break;
            case "Class":
                lesson = model.getLesson(
                        viewState.getLessonID(),
                        model.getClassByName(viewState.getClassName())
                );
                break;
        }


        assert lesson != null;
        subject.set(lesson.getSubject());
        topic.set(lesson.getTopic());
        contents.set(lesson.getContents());
        homework.set(lesson.getHomework());
        teacher.set(lesson.getTeacher().getName());
        date.set(lesson.getLessonDate().getDate());
        className.set("Class " + lesson.getClassName());
    }

    public void loadLessonDataFromStudent(Lesson lesson, Student student) {
        LessonData data = model.getLessonData(lesson, student);

        if (data.getAbsence() != null) {
            absent.set(  data.getAbsence().isWasAbsent()? "YES" : "---");
            motive.set(data.getAbsence().getMotive());
        }

        if (data.getGrade() != null) {
            grade.set( Integer.toString( data.getGrade().getGrade() ));
            comment.set(data.getGrade().getComment());
        }

    }


    public void clear() {
        //TODO clear the rest property
        error.set("");

    }

    public boolean submitChangeLesson() {
        try {
            switch (viewState.getAccessLevel()) {
                case "Student":
                    return model.changeMotive(viewState.getStudentID(), viewState.getLessonID(), motive.get());
                case "Administrator":
                    return model.changeLesson(viewState.getLessonID(), topic.get(), contents.get(), homework.get());
            }
        }catch (SQLException e){
            e.printStackTrace();
            error.set(e.getLocalizedMessage());
        }
        //TODO use observer to change lesson for all
        return false;
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater(() -> {
            PackageGrade packageGrade = (PackageGrade) event.getValue2();

            if (!viewState.getStudentID().equals(packageGrade.getID()) ||
                    !viewState.getLessonID().equals(packageGrade.getLessonID())) {
                return;
            }

            switch (event.getPropertyName()) {
                case "ChangeGradeComment":
                    grade.set(packageGrade.getGrade() + "");
                    comment.set(packageGrade.getComment());

                    break;
            }
        });
    }
}
