package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;
import model.packages.*;
import model.packages.Package;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class InfoViewModel implements LocalListener<String, Package> {
    private StringProperty subject;
    private StringProperty topic;
    private StringProperty contents;
    private StringProperty homework;
    private StringProperty teacher;
    private StringProperty classRoom;
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
        this.model.addListener(this, "ChangeLesson", "ChangeAbsence", "ChangeGradeComment");
        this.viewState = viewModelState;

        //Lesson properties
        this.subject = new SimpleStringProperty();
        this.topic = new SimpleStringProperty();
        this.contents = new SimpleStringProperty();
        this.homework = new SimpleStringProperty();
        this.teacher = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.classRoom = new SimpleStringProperty();
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

    public StringProperty getClassRoom() {
        return classRoom;
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
        teacher.set(lesson.getTeacher().getName() + "(" + lesson.getTeacher().getID() + ")");
        date.set(lesson.getLessonDate());
        className.set("Class " + lesson.getClassName());
        classRoom.set(lesson.getClassroom());
    }

    public void loadLessonDataFromStudent(Lesson lesson, Student student) {
        LessonData data = model.getLessonData(lesson, student);

        if (data.getAbsence() != null) {
            absent.set(  data.getAbsence().wasAbsent()? "YES" : "---");
            motive.set(data.getAbsence().getMotive());
        }

        if (data.getGrade() != null) {
            grade.set( (data.getGrade().getGrade() == -1)? "" : String.valueOf(data.getGrade().getGrade()));
            comment.set(data.getGrade().getComment());
        }

    }


    public void clear() {
        error.set("");

    }

    public boolean submitChangeLesson() {
        try {
            switch (viewState.getAccessLevel()) {
                case "Student":
                    return model.changeMotive(viewState.getStudentID(), viewState.getLessonID(), motive.get());
                case "Administrator":
                case "Teacher":
                    return model.changeLesson(viewState.getLessonID(), topic.get(), contents.get(), homework.get(),(teacher.get().contains("(")) ? teacher.get().split("[()]")[1] : "000000");
            }
        }catch (SQLException e){
            e.printStackTrace();
            error.set(e.getLocalizedMessage());
        }
        return false;
    }

    private boolean isTheWrongData(String studentID, String lessonID) {
        return !Objects.equals(viewState.getStudentID(), studentID) ||
                !Objects.equals(viewState.getLessonID(), lessonID);
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater(() -> {
            switch (event.getPropertyName()) {
                case "ChangeLesson" :
                    PackageLessonInfo packageLessonInfo = (PackageLessonInfo) event.getValue2();
                    if (!packageLessonInfo.getID().equals(viewState.getLessonID())) {
                        return;
                    }
                    topic.set(packageLessonInfo.getTopic());
                    contents.set(packageLessonInfo.getContents());
                    homework.set(packageLessonInfo.getHomework());
                    teacher.set(model.getTeacherBy(packageLessonInfo.getTeacherID()).getName() + "(" + packageLessonInfo.getTeacherID() +")");
                    break;
                case "ChangeAbsence":
                    PackageAbsence packageAbsence = (PackageAbsence) event.getValue2();
                    if (isTheWrongData(packageAbsence.getID(), packageAbsence.getLessonID())) {
                        return;
                    }
                    absent.set(packageAbsence.isAbsent()? "YES" : "---");
                    break;
                case "ChangeGradeComment":
                    PackageGrade packageGrade = (PackageGrade) event.getValue2();
                    if (isTheWrongData(packageGrade.getID(), packageGrade.getLessonID())) {
                        return;
                    }
                    grade.set(packageGrade.getGrade() + "");
                    comment.set(packageGrade.getComment());

                    break;
            }
        });
    }

    public ArrayList<String> getTeacherList() {
        ArrayList<String> teachers = new ArrayList<>();
        for (Teacher teacher: model.getAllTeachers()){
            teachers.add((String.format("%s (%S)",teacher.getName(), teacher.getID())));

        }
        return teachers;
    }

}
