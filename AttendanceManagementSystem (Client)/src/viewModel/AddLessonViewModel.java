package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Lesson;
import model.Model;
import model.Teacher;
import model.Class;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddLessonViewModel {

    private Model model;
    private ViewModelState viewModelState;

    private StringProperty topic;
    private StringProperty contents;
    private StringProperty homework;
    private StringProperty teacher;
    private StringProperty error;
    private StringProperty subject;
    private StringProperty startTime;
    private StringProperty endTime;
    private ObjectProperty<LocalDate> date;
    private StringProperty classroom;

    public AddLessonViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.viewModelState = viewModelState;

        //Lesson properties
        this.subject = new SimpleStringProperty();
        this.topic = new SimpleStringProperty();
        this.contents = new SimpleStringProperty();
        this.homework = new SimpleStringProperty();
        this.teacher = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>(LocalDate.now());
        this.classroom = new SimpleStringProperty();
        this.startTime = new SimpleStringProperty();
        this.endTime = new SimpleStringProperty();

        this.error = new SimpleStringProperty();
    }

    public void clear(){
        topic.set("");
        contents.set("");
        homework.set("");
        teacher.set("");
        error.set("");
        subject.set("");
        startTime.set("");
        endTime.set("");
        date.set(LocalDate.now());
        classroom.set("");
    }

    public boolean createLesson(){
       try {
           String teacherID = (teacher.get().contains("(")) ? teacher.get().split("[()]")[1] : "no id";
           Teacher teacher = model.getTeacherBy(teacherID);
           Class aClass = model.getClassByName(viewModelState.getClassName());
           model.addLesson(aClass, new Lesson(
                   teacher,
                   date.getValue(),
                   LocalTime.parse(startTime.get()),
                   LocalTime.parse(endTime.get()),
                   subject.get(),
                   topic.get(),
                   contents.get(),
                   classroom.get(),
                   homework.get(),
                   viewModelState.getClassName())
           );
           return true;
       }

       catch (NullPointerException | IllegalArgumentException e){
           error.set("Please make sure to fill out all the fields");
           return false;
       }
       catch (DateTimeParseException parseException){
           error.set("Please fill out the lesson time in this format 'hh:mm' for example '09:20'");
           startTime.set("");
           endTime.set("");
           return false;
       } catch (SQLException throwables) {
           throwables.printStackTrace();
           return false;
       }
    }


    public StringProperty topicProperty() {
        return topic;
    }

    public StringProperty contentsProperty() {
        return contents;
    }

    public StringProperty homeworkProperty() {
        return homework;
    }

    public StringProperty teacherProperty() {
        return teacher;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public StringProperty classroomProperty() {
        return classroom;
    }

    public ArrayList<String> getTeacherList() {
        ArrayList<String> teachers = new ArrayList<>();
        for (Teacher teacher: model.getAllTeachers()){
            teachers.add((String.format("%s (%S)",teacher.getName(), teacher.getID())));

        }
        return teachers;
    }
}
